/*
 * This is the PetController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
// Local Imports
import com.pg.pet_grooming.Services.PetService;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.PetOwner;


@Controller
public class PetController {
    
   // Inject PetService
    @Autowired private PetService petService;
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;
    
        
    @GetMapping("/newCustomer/pet/{id}")
    public String newCustomerPet(Model model,@PathVariable("id")int id) throws ResourceNotFoundException {
       PetOwner petOwner = new PetOwner();
       Pet pet = new Pet();
         // Set Page Title
        String pageTitle = "New Customer";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("id", id);
        model.addAttribute("pet",pet);
        model.addAttribute("petOwner", petOwnerService.findPetOwnerById(id));
        return "NewPet";
    }
    
        
     // Add New Pet
    @PostMapping("/newCustomer/pet/new")
    public String addPet(Pet pet, Model model){
       petService.save(pet);
       return "redirect:/newCustomer/pet/new";
    }  
    
    
    // Get Pet by PetOwnerId
    @GetMapping("/petOwner/{petOwnerId}/pets")
    public List<Pet> getPetsByPetOwnerId(@PathVariable(value="petId")Integer petOwnerId){
        return petRepository.findByPetOwnerId(petOwnerId);
    }
    
    // Save Pet 
    @PostMapping("petOwners/{petOwnerId}/pets")
    public Pet addPet(@PathVariable(value="petOwnerId") Integer petOwnerId,
        @Valid @RequestBody Pet pet) throws ResourceNotFoundException{
            return petOwnerRepository.findById(petOwnerId).map(petOwner -> {
                pet.setPetOwner(petOwner);
                return petRepository.save(pet);
            }).orElseThrow(()-> new ResourceNotFoundException("Pet owner not found"));
        }
    
}
