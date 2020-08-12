/*
 * This is the Customer Controller Class
 * This class will handle the CRUD operations and Thymeleaf.
 * 
 */
package com.pg.pet_grooming.Controllers;
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CustomerController {
    
    @GetMapping("/customers")
    public String newAppointment(Model model){
        
        return "NewAppointment";
    }
}
