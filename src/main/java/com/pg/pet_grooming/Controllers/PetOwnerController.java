/*
 * This is the Pet Owner Controller Class
 * CRUD operations for PetOwner will be handled here.
 */
package com.pg.pet_grooming.Controllers;
// Imports
import com.pg.pet_grooming.Models.Auditable;
import com.pg.pet_grooming.Models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;
// Local Imports
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;



@Controller
public class PetOwnerController {

    // Inject Service
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetOwnerRepository petOwnerRepository;
    
    // Get Pet Owners
    @GetMapping("/getPetOwners")
    public List<PetOwner>getPetOwners(){
        return petOwnerService.getPetOwners();
    }
    
    // Get New Customer = New Pet Owner Page
    @RequestMapping("/newCustomer/petOwner")
    public String newCustomer(Model model){
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
    
     // Add New PetOwner (Customer)
    @PostMapping("/newCustomer/petOwner/new")
    public String addPetOwner(PetOwner petOwner){
       petOwnerRepository.save(petOwner);
       // Set PathVariable In PetController to New Pet Owner Id
       int id = petOwner.getId();
        System.out.println(id);
       // Direct to New Pet and pass Pet Owner Id
       return "redirect:/newCustomer/pet/"+id;
    }  
    

    
    // Find Pet Owner by ID
    @RequestMapping("/petOwners/{id}")
    public ResponseEntity<PetOwner>getPetOwnerById(
    @PathVariable(value="id") int petOwnerId) throws ResourceNotFoundException{
        PetOwner petOwner = petOwnerService.findById(petOwnerId)
        .orElseThrow(() -> new ResourceNotFoundException("Pet Owner not found :: " + petOwnerId));
        return ResponseEntity.ok().body(petOwner);
     
    }
    
    
    // Update Pet Owner
    @RequestMapping(value="/customers/update/petOwner",method = {RequestMethod.PUT,RequestMethod.GET})
    public void updatePetOwner(PetOwner petOwner){
        petOwnerService.save(petOwner);
    }
    
    // Delete Pet Owner
   @RequestMapping(value="/customers/delete/petOwner",method={RequestMethod.DELETE,RequestMethod.GET})
   public void deletePetOwner(Integer id){
       petOwnerService.delete(id);
   }
}
