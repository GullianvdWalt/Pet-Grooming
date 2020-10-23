/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Controllers;
// Imports

import com.pg.pet_grooming.DTO.PetOwnerPet;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Services.PetService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetService petService;
    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;

    // Main Customer Dashboard
    @RequestMapping("/customers")
    public String Customer(Model model,@Param("keyword") String keyword) {


        // Set Page Title
        String pageTitle = "Customers";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);
        
        if(keyword == null){
            
        // Get PetOwners and then pet list can be retrieved and combined for customers view
            List<PetOwner> customerList = petOwnerService.getPetOwners();
            model.addAttribute("customerList", customerList);
            
            
        }else
        {
            // Search has been made via pet name
            if(searchPet(keyword) == true){
              // Get pet matching keyword with SQL query 
              Pet pet = petService.findPetByKeyword(keyword);
              // Add to a list PetOwner has only list of Pets (Many to one)
              List<Pet> petList = new ArrayList<>();
              petList.add(pet);
             // Get Pet Owner via pet
             PetOwner petOwner = pet.getPetOwner();
             // Set the pet owner pet based on keyword SQL query
             petOwner.setPets(petList);
            // Add pet owner to customer list
            List<PetOwner> customerList = new ArrayList<>();
            customerList.add(petOwner);
            model.addAttribute("customerList", customerList);

          }else{
            // Search is based on pet owner details
            List<PetOwner> customerList = petOwnerService.findPetOwnerByKeyword(keyword);
            model.addAttribute("customerList", customerList);
          }
            
        }

        model.addAttribute("keyword", keyword);
        return "Customers";
    }

    // View Customer Details
    @RequestMapping("/customerDetails/{ownerId}")
    public String viewCustomerDetails(@PathVariable(name = "ownerId") Integer petOwnerId,
            Model model) throws ResourceNotFoundException {

        // Objects
        Pet pet = new Pet();
        // Pet List : all by owner id
        List<Pet> petList = petRepository.findByPetOwnerId(petOwnerId);
        // Set Page Title
        String pageTitle = "Edit Customer";
        model.addAttribute("pageTitle", pageTitle);

        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);

        if (!petOwnerId.equals(null)) {
            PetOwner petOwnerEntity = petOwnerService.findPetOwnerById(petOwnerId);
            model.addAttribute("petOwner", petOwnerEntity);
        } else {
            return "redirect:/customers";
        }
        // Set 
        model.addAttribute("petList", petList);
        model.addAttribute("pet", pet);
        return "CustomerDetails";
    }

    // Check if the keyword is pet name
    public boolean searchPet(String keyword){
        // No Search
        if(keyword.equals(null)){
        }

        // Get All Pets
        List<Pet> petList = petService.getPets();
        
        // Loop through all pets and check if keyword was pet name
        for (int i = 0; i < petList.size(); i++) {
            if(petList.get(i).getPet_name().equals(keyword)){
                return true;
            }
        }
        
        return false;
    }
}


