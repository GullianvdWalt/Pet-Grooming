/*
 * This is the PetController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Local Imports
import com.pg.pet_grooming.Services.PetService;

@Controller
public class PetController {
    
   // Inject PetService
    @Autowired private PetService petService;
    
    // New Pet
    @RequestMapping("/newPet")
    public String addNewPet(){
        return "/customers";
    }
    
}
