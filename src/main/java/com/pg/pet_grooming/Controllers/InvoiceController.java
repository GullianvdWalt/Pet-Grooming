/*
    Created By Gullian Van Der Walt
 */
package com.pg.pet_grooming.Controllers;

//Imports
import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Models.Employees;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
// Local Imports
import com.pg.pet_grooming.Services.InvoiceService;
import com.pg.pet_grooming.Models.Invoice;
import com.pg.pet_grooming.Models.PastAppointments;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.PastAppointmentRepo;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Repositories.ServicesRepository;
import com.pg.pet_grooming.Services.EmployeeService;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Services.ServicesService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvoiceController {

    // Inject Service    
    @Autowired private InvoiceService invoiceService;    
    @Autowired private PastAppointmentRepo pastAppRepo;
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private ServicesService servicesService;
    @Autowired private PetRepository petRepository;
    @Autowired private ServicesRepository servicesRepository;
    @Autowired private EmployeeService employeeService;
    
    // New Invoice
    @RequestMapping("/invoice/new/{id}")
    public String newInvoice (Model model,@PathVariable("id")Integer id) throws ResourceNotFoundException{
        
          // Get Appointment by Id from Path variable
      PastAppointments pastApp = pastAppRepo.getOne(id);
      // Add to view
      model.addAttribute("appointment",pastApp);
      
      // Get list of services
      List<Services> listServices = pastApp.getServices();
      // Add to view
      model.addAttribute("listServices", listServices);
      
      // Get List of Employees(Groomers)
      List<Employees> employeeList = employeeService.getEmployees();
      // Add to view
      model.addAttribute("employeeList", employeeList);
      
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
    // Invoice By Appointment
    @RequestMapping("/finance/viewInvoices")
    public String viewInvoices (Model model){
      // Set Page Title
      String pageTitle = "Invoices";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "invoice2.png";
      model.addAttribute("iconUrl", iconUrl);
      return "ViewInvoices";
    }
     
    // Export to PDF
    
    
    
    @RequestMapping("/invoiceComplete")
    public String invoiceComplete (Model model){
      // Set Page Title
      String pageTitle = "Invoice Complete";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "Invoice.png";
      model.addAttribute("iconUrl", iconUrl);
      return "InvoiceComplete";
    }
}
