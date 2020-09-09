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
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public String addPet(@Valid @ModelAttribute("pet") Pet pet,BindingResult bindingResult,Model model){
       
//       bindingResult.addError(new FieldError("pet", "id", ""));
        
//       int petId = pet.getId();
       int ownerId = pet.getPet_owner_id();
       
       if(bindingResult.hasErrors()){
           return "newPetForm";
       }
       
       petService.save(pet);
       // Direct to New Pet and pass Pet Owner Id
       return "redirect:/customerDetails/"+ownerId;
    }  
    
    // Get Pet By ID
    @RequestMapping("/pet/findPetById/{ID}")
    @ResponseBody
    public Optional<Pet>GetEditPetById(@PathVariable("ID")int id){
        System.out.println(id);
        return petRepository.findById(id);
    }
    
//    // Get Pet by PetOwnerId
//    @GetMapping("/petOwner/{petOwnerId}/pets")
//    public List<Pet> getPetsByPetOwnerId(@PathVariable(value="ID")Integer petOwnerId){
//         
//        return petService.getPetbyId(petOwnerId);
//    }
    
    // Save Pet 
    @PostMapping("petOwners/{petOwnerId}/pets")
    public Pet addNewPet(@PathVariable(value="petOwnerId") Integer petOwnerId,
        @Valid @RequestBody Pet pet) throws ResourceNotFoundException{
            return petOwnerRepository.findById(petOwnerId).map(petOwner -> {
                pet.setPetOwner(petOwner);
                return petRepository.save(pet);
            }).orElseThrow(()-> new ResourceNotFoundException("Pet owner not found"));
        }
           
//    // Create Pet or Update Pet
//    @RequestMapping(path="/editPet", method={RequestMethod.PUT,RequestMethod.GET})
//    public String createOrUpdatePet(Pet pet){        
//        petService.createOrUpdatePet(pet);
//        return "redirect:/customers";
//    }
    
        // Create Pet or Update Pet
    @RequestMapping(value="/pet/update", method={RequestMethod.PUT,RequestMethod.GET})
    public String UpdatePet(Pet pet){        
        petService.save(pet);
        return "redirect:/customers";
    }
    
    @RequestMapping(value="/pet/delete", method={RequestMethod.DELETE,RequestMethod.GET})
    public String deletePet(Integer id){
        //, @PathVariable("pet_owner_id")int petOwnerId
        petService.deletePet(id);
        return "redirect:/customers";
    }
    
    
//        // JS Ajax Controller for setting pet fields on select
//    @RequestMapping(value="/loadPetDetails", method={RequestMethod.GET,RequestMethod.POST})
//    public Pet setPetFields(@RequestParam(value="ID")int pId, Model model){
//        
//        Pet petEntity = null;
//        Optional<Pet> optionalPet = petService.findPetById(pId);
//        
//        if(optionalPet.isPresent()){
//            petEntity = optionalPet.get();     
//        }
//        model.addAttribute("pets",petEntity);
//        return petEntity;
//    }
    
}
