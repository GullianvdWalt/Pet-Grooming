/*
 * This is the InvoiceController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
// Local Imports
import com.pg.pet_grooming.Services.InvoiceService;
import com.pg.pet_grooming.Models.Invoice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvoiceController {

    // Inject Service    
    @Autowired InvoiceService invoiceService;    
    
//    @GetMapping("/invoices")
//    public String getInvoices(Model model){
//        List<Invoice> invoiceList = invoiceService.getInvoices();
//        return "Invoice";
//    }
    @RequestMapping("/invoice")
    public String getInvoice (Model model){
      // Set Page Title
      String pageTitle = "Invoice";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "Invoice.png";
      model.addAttribute("iconUrl", iconUrl);
      return "Invoice";
    }
    
    @RequestMapping("/viewInvoices")
    public String viewInvoices (Model model){
      // Set Page Title
      String pageTitle = "Invoices";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "invoice2.png";
      model.addAttribute("iconUrl", iconUrl);
      return "ViewInvoices";
    }
        
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
