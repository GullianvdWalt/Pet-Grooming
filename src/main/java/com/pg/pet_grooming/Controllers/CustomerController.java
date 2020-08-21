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
    public String Customer(){
        
        return "Customers";
    }
    
    // New Customer
    @RequestMapping("/newCustomer")
    public String newCustomer(){
        
        return "NewCustomer";
    }
}
