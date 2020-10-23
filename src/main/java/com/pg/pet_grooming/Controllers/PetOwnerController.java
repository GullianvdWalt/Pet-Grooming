/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Controllers;
// Imports

import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Services.PetOwnerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PetOwnerController {

    // Inject Service
    @Autowired
    private PetOwnerService petOwnerService;
    // Inject Repository
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    // Get Pet Owners
    @GetMapping("/getPetOwners")
    public List<PetOwner> getPetOwners(BindingResult bindingResult, Model model) {
        
        return petOwnerService.getPetOwners();
    }

    // Get New Customer = New Pet Owner Page
    @RequestMapping("/newCustomer/petOwner")
    public String newCustomer(Model model) {
        PetOwner petOwner = new PetOwner();
        Pet pet = new Pet();
        // Set Page Title
        String pageTitle = "New Customer";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "petOwner.png";
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("petOwner", petOwner);

        return "NewPetOwner";
    }

    // Add New PetOwner(Customer)
    @PostMapping("/newCustomer/petOwner/new")
    public String addPetOwner(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
        // Set PathVariable In PetController to New Pet Owner Id
        int id = petOwner.getId();

        // Direct to New Pet and pass Pet Owner Id
        return "redirect:/newCustomer/pet/" + id;
    }

    // Find Pet Owner by ID
    @RequestMapping("/petOwners/{id}")
    public ResponseEntity<PetOwner> getPetOwnerById(
            @PathVariable(value = "id") int petOwnerId) throws ResourceNotFoundException {
        PetOwner petOwner = petOwnerService.findById(petOwnerId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet Owner not found :: " + petOwnerId));
        return ResponseEntity.ok().body(petOwner);

    }

    // Update Pet Owner
    @RequestMapping("/customers/update/petOwner/{id}")
    public String updatePetOwner(@PathVariable int id, Model model) {
        PetOwner petOwner = petOwnerService.findById(id).get();
        model.addAttribute("petOwner", petOwner);
        petOwnerService.save(petOwner);

        return "CustomerDetails";
    }

    // Edit PetOwner
    @RequestMapping(path = "/createNewPetOwner", method = {RequestMethod.POST, RequestMethod.PUT})
    public String createOrUpdatePetOwner(PetOwner petOwner, @RequestParam("id") int petOwnerId) {
        petOwnerId = petOwner.getId();
        petOwnerService.createOrUpdatePetOwner(petOwner);
        if (petOwnerId >= 1) {
            return "redirect:/customerDetails/" + petOwnerId;
        }
        return "redirect:/customers";
    }

    // Delete Pet Owner
    @RequestMapping(value = "/customers/delete/petOwner", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deletePetOwner(Integer id) {
        petOwnerService.delete(id);
        return "redirect:/customers";
    }
}

