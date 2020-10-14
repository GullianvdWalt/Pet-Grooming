/*
 * This is the Pet Owner Controller Class
 * CRUD operations for PetOwner will be handled here.
 * 
 */
package com.pg.pet_grooming.Controllers;
// Imports

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
// Local Imports
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Models.PetOwner;

@Controller
public class PetOwnerController {

    @Autowired
    private PetOwnerService petOwnerService;

    // Add New PetOwner (Customer)
    @PostMapping("/addPetOwner")
    public String addCustomer(PetOwner petOwner) {
        petOwnerService.save(petOwner);

        return "redirect:/newPet";
    }

}
