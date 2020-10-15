/*
    Created By Gullian Van Der Walt
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.io.IOException;
import java.text.ParseException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Date;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
// Local Imports
import com.pg.pet_grooming.Services.InvoiceService;
import com.pg.pet_grooming.Models.Invoice;
import com.pg.pet_grooming.Models.PastAppointments;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.PastAppointmentRepo;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.EmployeeService;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Models.BusinessDetails;
import com.pg.pet_grooming.Models.Employees;
import com.pg.pet_grooming.Models.Income;
import com.pg.pet_grooming.Repositories.BusinessDetailsRepository;
import com.pg.pet_grooming.Repositories.InvoiceRepository;
import com.pg.pet_grooming.Services.IncomeService;





@Controller
public class InvoiceController {

    // Inject Service    
    @Autowired private InvoiceService invoiceService;    
    @Autowired private PastAppointmentRepo pastAppRepo;
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetRepository petRepository;
    @Autowired private EmployeeService employeeService;
    @Autowired private BusinessDetailsRepository businessDetailsRepo;
    @Autowired private IncomeService incomeService;
    @Autowired private InvoiceRepository invoiceRepository;

    
    // New Invoice with Past Appointment Id
    @RequestMapping("/invoice/new/{id}")
    public String newInvoice (Model model,@PathVariable("id")Integer id) throws ResourceNotFoundException{
        
          // Get Appointment by Id from Path variable
      PastAppointments pastApp = pastAppRepo.getOne(id);
      // Add to view
      model.addAttribute("pastApp",pastApp);
           
      // Get list of services
      List<Services> listServices = pastApp.getServices();
      // Add to view
      model.addAttribute("listServices", listServices);
      // Services Object
      Services service = new Services();
      // BusinessDetails Object
      BusinessDetails businessDetails = new BusinessDetails();
      businessDetails = businessDetailsRepo.getOne(1);
      
      double subtotal = 0;
      double total = 0;
      int numDiscountApp = businessDetails.getDiscount_num_app();
      double discountRate = businessDetails.getDiscount_rate();
      double discount = discountRate/ 100;

      if(listServices.isEmpty()){
      
      }
        // Calcualte subtotal
        for (int i = 0; i < listServices.size(); i++) {
            // Get each service
            service = listServices.get(i);
            subtotal = subtotal + service.getService_price();
        }
      // Get Numeber of appointments customer has had in the past
      int numApp = pastAppRepo.getAppCount(pastApp.getPet_owner_id());
      // Test Discount
      numApp = 6;
      // Check if there is a discount based on the number of appointments required
      if(numApp >= numDiscountApp){
          total = subtotal - (subtotal * discount);
          model.addAttribute("discount", (subtotal * discount));
          model.addAttribute("total", total);

      }else{
          total = subtotal;
          model.addAttribute("discount", "None");
          model.addAttribute("total", total);
      }
      
      
      // Get List of Employees(Groomers)
      int emp_id = pastApp.getEmployee_id();
      Employees employee = new Employees();
      employee = employeeService.getEmployeeById(emp_id);
      // Add to view
      model.addAttribute("employee", employee);
      
      // Get PetOwner
      PetOwner petOwner = petOwnerService.findPetOwnerById(pastApp.getPet_owner_id());
      // Add To View
      model.addAttribute("petOwner", petOwner);   
      
      // Get Pet
      Pet pet = petRepository.getOne(pastApp.getPet_id());
      // Add to view
      model.addAttribute("pet", pet);    
        
      // Set Page Title
      String pageTitle = "New Invoice";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "invoice2.png";
      model.addAttribute("iconUrl", iconUrl);
      return "Invoice";
    }
    
    // Save Invoice and go To Invoice Temaplate
    @RequestMapping(value = "/invoice/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveInvoice(Model model,Invoice invoice,
            @RequestParam("total")Double total,
            @RequestParam("invoice_date") String invoice_date,
            RedirectAttributes redirAttrs)throws ParseException, IOException {
    
       // Convert String to date and set invoice date
        // DateFormatter
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ROOT);
        Date invoiceDate = (Date) dateTimeFormat.parse(invoice_date);
        invoice.setInvoice_date(invoiceDate);
        
        // Save Invoice
        invoiceService.save(invoice);
        // Save to income
        Income income = new Income();
        income.setInvoice_num(invoice.getInvoice_num());
        income.setAmount(total);
        // Save Income
        incomeService.saveIncome(income);
        
        // Redirect to Invoice Template 
        redirAttrs.addFlashAttribute("success", "Invoice Saved!");
        
        return "redirect:/invoices/template/"+invoice.getInvoice_num();
    }
    
    // Template of invoice to export
    @RequestMapping("/invoices/template/{id}")
    public String exportToPdf(@PathVariable("id") Integer id, Model model){
     
        // Get Business Details
        BusinessDetails businessDetails = new BusinessDetails();
        businessDetails = businessDetailsRepo.getOne(1);  
        
        // Get Invoice 
        Invoice invoice = new Invoice();
        invoice = invoiceRepository.getOne(id);
        
        // Get Past Appointment
        PastAppointments pastApp = invoice.getPastAppointment();
        // Get Services
        List<Services> services = pastApp.getServices();
        
        // Get Employee (Groomer)
        Employees employee = new Employees();
        employee = employeeService.getEmployeeById(pastApp.getEmployee_id());
        
        //Get Pet Size
        Pet pet = new Pet();
        pet = petRepository.getOne(pastApp.getPet_id());
        
        // Add Objects to view
        model.addAttribute("businessDetails", businessDetails);
        model.addAttribute("invoice", invoice);
        model.addAttribute("pastApp", pastApp);
        model.addAttribute("servicesList", services);
        model.addAttribute("employee", employee);
        model.addAttribute("petSize", pet.getPet_size());
        
        // Set Page Title
        String pageTitle = "Invoice Preview";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "invoice2.png";
        model.addAttribute("iconUrl", iconUrl);
        return "InvoiceTemplate";
    }
    
    
    
    
    
    // View Invoices
    @RequestMapping("/finance/viewInvoices")
    public String viewInvoices(Model model) {
        
        //List<Invoice> invoices = 
        
        // Set Page Title
        String pageTitle = "Invoices";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "invoice2.png";
        model.addAttribute("iconUrl", iconUrl);
        return "ViewInvoices";
    }

}

