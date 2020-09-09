/*
   Created By Gullian Van Der Walt 01/08/2020
   Last Update: 2020/09/07, 05:31

 * This is the Customer Controller Class
 * This Controller will be responsible for the routing of the customer pages
 */
package com.pg.pet_grooming.Controllers;
// Imports
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.pg.pet_grooming.Models.Auditable;
import javax.json.Json;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson; 

// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Services.PetService;







@Controller
public class CustomerController{
    
    // Inject Services
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetService petService;
    @Autowired private PetRepository petRepository;
    
    // Main Customer Dashboard
    @RequestMapping("/customers")
    public String Customer(Model model){
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
        System.out.println(petOwnerId);
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
    
//    // Edit Customer
//    @RequestMapping("/editCustomer")
//    public String EditCustomer(Model model){
//         // Set Page Title
//        String pageTitle = "Customers";
//        model.addAttribute("pageTitle", pageTitle);
//        // Set Page Title Icon
//        String iconUrl = "dog.jpg";
//        model.addAttribute("iconUrl", iconUrl);
//        return "EditCustomer";
//    }
}
