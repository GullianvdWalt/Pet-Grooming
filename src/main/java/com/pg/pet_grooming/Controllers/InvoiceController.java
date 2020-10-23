/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Controllers;

//Imports
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
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
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;





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
    public String exportInvoiceToPdf(@PathVariable("id") Integer id, Model model){
     
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
    
        // Method to Create PDF with Velocity(HTML to PDF)
        @GetMapping("/genInvoicePdf/{fileName}/{id}")
        HttpEntity<byte[]> createPdf(@PathVariable("fileName") String fileName,
            @PathVariable("id") Integer id) throws IOException {
    
            
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
            
            
            // Get and Initialize Velocity Engine
        VelocityEngine ve = new VelocityEngine();

        // Get template
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());
        ve.init();
        // Set Template to HTML file
        Template template = ve.getTemplate("templates/invoiceExport.vm");
        // Create Context and add data
        VelocityContext context = new VelocityContext();
            System.out.println(businessDetails.getImagePath());
        // Add Data
        context.put("logo",businessDetails.getImagePath());
        context.put("invoiceNum", invoice.getInvoice_num());
        context.put("invoiceDate",invoice.getInvoice_date());
        context.put("paymentMethod",invoice.getPayment_method());
        context.put("businessName", businessDetails.getBusiness_name());
        context.put("businessCell", businessDetails.getBusiness_cell());
        context.put("businessEmail", businessDetails.getBusiness_email());
        context.put("businessAddress", businessDetails.getBusiness_address());
        context.put("petOwnerName",pastApp.getPet_owner_full_name());
        context.put("petOwnerCell",pastApp.getPet_owner_cell());
        context.put("petOwnerAddress",pastApp.getPet_owner_address());
        context.put("petName",pastApp.getPet_name());
        context.put("petBreed",pastApp.getPet_breed() );
        context.put("petSize", pet.getPet_size());
        context.put("employeeName",employee.getEmployee_full_name());
        context.put("services", services);
        context.put("discount", invoice.getDiscount());
        context.put("total", invoice.getTotal());
        
                // Render Template Into Stringwriter
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        System.out.println(writer.toString());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = generatePdf(writer.toString());
        fileName = "Invoice_" + pastApp.getPet_owner_full_name() + "_" + invoice.getInvoice_date();
        // Create Header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; fileName=" + fileName.replace(" ", "_"));
        header.setContentLength(baos.toByteArray().length);

        return new HttpEntity<byte[]>(baos.toByteArray(), header);
        }
    
        // Generate PDF
        private ByteArrayOutputStream generatePdf(String html) {
        String pdfFilePath = "";
        PdfWriter pdfWriter = null;

        // Create New Document
        Document document = new Document();

        try {
            document = new Document();
            // Document Header Attributes
            document.addAuthor("Gullian Van Der Walt");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("Gullian Van Der Walt");
            document.addTitle("PAYSLIP");
            document.setPageSize(PageSize.A4);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            // Open Document
            document.open();

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);
            xmlWorkerHelper.getDefaultCSS();
            // Convert HTML
            xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(html));

            // Close The Document
            document.close();
            System.out.println("PDF generated Successfully");

            return baos;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    
    // View Invoices
    @RequestMapping("/finance/viewInvoices")
    public String viewInvoices(Model model) {
        
        List<Invoice> invoiceList = invoiceService.getInvoices();
        Invoice invoice = new Invoice();
        List<PastAppointments> pastAppList = new ArrayList<>();
        PastAppointments pastApp = new PastAppointments();
        
        for (int i = 0; i < invoiceList.size(); i++) {
            // Get Invoice objects
            invoice = invoiceList.get(i);
            // Get Past Appointments
            pastApp = pastAppRepo.getOne(invoice.getPast_app_id());
            // Add to PastApp List
            pastAppList.add(pastApp);
        }
        // Add List of objects to view
        model.addAttribute("invoiceList", invoiceList);
        model.addAttribute("pastAppList", pastAppList);
        
        // Set Page Title
        String pageTitle = "Invoices";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "invoice2.png";
        model.addAttribute("iconUrl", iconUrl);
        return "ViewInvoices";
    }

}

