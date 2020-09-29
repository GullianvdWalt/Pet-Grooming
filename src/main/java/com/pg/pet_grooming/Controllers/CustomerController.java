/*
   Created By Gullian Van Der Walt 01/08/2020
   Last Update: 2020/09/29, 08:33

 * This is the Customer Controller Class
 * This Controller will be responsible for the routing of the customer pages
 */
package com.pg.pet_grooming.Controllers;
// Imports

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Services.PetService;
import com.pg.pet_grooming.DTO.PetOwnerPet;


@Controller
public class CustomerController{
    
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetService petService;
    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;
    
    // Main Customer Dashboard
    @RequestMapping("/customers")
    public String Customer(Model model, Pet pet, PetOwner petOwner,PetOwnerPet petOwnerPet){
       
//      // Get Pets
      List<Pet> petList = petService.getPets();
      
      // Customers (PetOwner + Pets)
      List<PetOwner> customerList = petOwnerRepository.findAll();
      
      model.addAttribute("pet", pet);
      
      model.addAttribute("customerList", customerList);
      model.addAttribute("petList", petList);
      // Set Page Title
      String pageTitle = "Customers";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "dog.jpg";
      model.addAttribute("iconUrl", iconUrl);
      return "Customers";
    }   
    
    // View Customer Details
    @RequestMapping("/customerDetails/{ownerId}")
    public String viewCustomerDetails(@PathVariable(name = "ownerId") Integer petOwnerId,
            Model model)throws ResourceNotFoundException{

        // Objects
        Pet pet = new Pet();
        // Pet List : all by owner id
        List <Pet> petList = petRepository.findByPetOwnerId(petOwnerId);
         // Set Page Title
        String pageTitle = "Edit Customer";
        model.addAttribute("pageTitle", pageTitle);
        
        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);
        
        if(!petOwnerId.equals(null)){
        PetOwner petOwnerEntity = petOwnerService.findPetOwnerById(petOwnerId);
        model.addAttribute("petOwner", petOwnerEntity);
        }else{
            return"redirect:/customers";
        }
        // Set 
        model.addAttribute("petList", petList);
        model.addAttribute("pet", pet);
        return "CustomerDetails";
    }       
    
}
