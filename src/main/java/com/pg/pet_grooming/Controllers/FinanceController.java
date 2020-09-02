/*
   Created By Gullian Van Der Walt

 * This is the FinanceController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import com.pg.pet_grooming.Models.Employees;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FinanceController {    
    
    // Main Finance Page
    @RequestMapping("/finance")
    public String showFinance(Model model){
      // Set Page Title
      String pageTitle = "Finance";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "finance.png";
      model.addAttribute("iconUrl", iconUrl);
      return "Finance";
    }
    
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
    
     @RequestMapping("/invoices")
    public String viewInvoices (Model model){
      // Set Page Title
      String pageTitle = "Invoices";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "invoice2.png";
      model.addAttribute("iconUrl", iconUrl);
      return "ViewInvoices";
    }
}
