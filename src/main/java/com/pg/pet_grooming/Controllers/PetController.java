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
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Services.PetService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PetController {

    // Inject PetService
    @Autowired
    private PetService petService;
    @Autowired
    private PetOwnerService petOwnerService;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @RequestMapping(value = "/newCustomer/pet/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String newCustomerPet(Model model, @PathVariable("id") int id) throws ResourceNotFoundException {
        PetOwner petOwner = new PetOwner();
        Pet pet = new Pet();
        // Set Page Title
        String pageTitle = "New Customer";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("id", id);
        model.addAttribute("pet", pet);
        model.addAttribute("petOwner", petOwnerService.findPetOwnerById(id));
        return "NewPet";
    }
    // Add New Pet

    @PostMapping("/newCustomer/pet/new")
    public String addPet(@Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult,
            RedirectAttributes redirAttrs,Model model) {
        int ownerId = pet.getPet_owner_id();
        if (bindingResult.hasErrors()) {
            return "newPetForm";
        }
        petService.save(pet);
        redirAttrs.addFlashAttribute("success", "Pet Saved!");
        // Direct to New Pet and pass Pet Owner Id
        return "redirect:/customerDetails/" + ownerId;
    }
    
    

    // Get Pet By ID
    @RequestMapping("/pet/findPetById/{ID}")
    @ResponseBody
    public Optional<Pet> GetEditPetById(@PathVariable("ID") int id) {
        return petRepository.findById(id);
    }

    // Save Pet 
    @PostMapping("petOwners/{petOwnerId}/pets")
    public Pet addNewPet(@PathVariable(value = "petOwnerId") Integer petOwnerId,
            @Valid @RequestBody Pet pet) throws ResourceNotFoundException {
        return petOwnerRepository.findById(petOwnerId).map(petOwner -> {
            pet.setPetOwner(petOwner);
            return petRepository.save(pet);
        }).orElseThrow(() -> new ResourceNotFoundException("Pet owner not found"));
    }

    // Create Pet or Update Pet
    @RequestMapping(value = "/pet/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String UpdatePet(Pet pet,  RedirectAttributes redirAttrs,@RequestParam("pet_owner_id") int petOwnerId) {
        petOwnerId = pet.getPet_owner_id();
        petService.save(pet);

        if (petOwnerId >= 1) {
            redirAttrs.addFlashAttribute("success", "Pet Updated!");
            return "redirect:/customerDetails/" + petOwnerId;
        }
        return "redirect:/customers";
    }

    // Delete Pet
    @RequestMapping(value = "/pet/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deletePet(Integer id) {
        //, @PathVariable("pet_owner_id")int petOwnerId
        petService.deletePet(id);
        return "redirect:/customers";
    }

}

