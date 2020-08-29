/*
 * This is the PetController Class
 * This class will handle the CRUD operations and Thymeleaf.
 * 
 */
package com.pg.pet_grooming.Controllers;

import com.pg.pet_grooming.Services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {
   // Inject PetService
    @Autowired
    private PetService petService;
    
    
        // New Customer
    @RequestMapping("/newPet")
    public String addNewPet(){
        return "/customers";
    }
    
}
