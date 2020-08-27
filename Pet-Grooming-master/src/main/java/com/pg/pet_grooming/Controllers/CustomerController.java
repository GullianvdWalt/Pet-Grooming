/*
 * This is the Customer Controller Class
   This Controller will be responsible for the routing of the customer pages
 * 
 * 
 */
package com.pg.pet_grooming.Controllers;
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomerController {
    

    // Main Customer Dashboard
    @RequestMapping("/customers")
    public String Customer(Model model){
      // Set Page Title
      String pageTitle = "Customers";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "dog.jpg";
      model.addAttribute("iconUrl", iconUrl);
        return "Customers";
    }
    
    // New Customer
    @RequestMapping("/newCustomer")
    public String newCustomer(Model model){
         // Set Page Title
        String pageTitle = "Customers";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);
        return "NewCustomer";
    }
    
    // Edit Customer
    @RequestMapping("/editCustomer")
    public String EditCustomer(Model model){
         // Set Page Title
        String pageTitle = "Customers";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);
        return "EditCustomer";
    }
}
