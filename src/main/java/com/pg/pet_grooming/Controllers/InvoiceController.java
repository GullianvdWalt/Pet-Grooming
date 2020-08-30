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

@Controller
public class InvoiceController {

    // Inject Service    
    @Autowired InvoiceService invoiceService;    
    
    @GetMapping("/invoices")
    public String getInvoices(Model model){
        List<Invoice> invoiceList = invoiceService.getInvoices();
        return "Invoice";
    }

}
