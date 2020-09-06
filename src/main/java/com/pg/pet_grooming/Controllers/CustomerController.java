/*
   Created By Gullian Van Der Walt

 * This is the Customer Controller Class
 * This Controller will be responsible for the routing of the customer pages
 */
package com.pg.pet_grooming.Controllers;
// Imports
import com.pg.pet_grooming.Models.Auditable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Services.PetOwnerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CustomerController{
    
    // PetOnwer Inject Service
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetOwnerRepository petOwnerRepository;

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
    

    
//    @GetMapping("/newCustomer/pet/new/{id}")
//    public String newCustomerPet(@PathVariable(name = "id") Integer petOwnerId,Model model){
//
//       List <PetOwner> petOwnerList = petOwnerService.findPetOwnerById(petOwnerId);
//       Pet pet = new Pet();
//       
//         // Set Page Title
//        String pageTitle = "New Customer";
//        model.addAttribute("pageTitle", pageTitle);
//        // Set Page Title Icon
//        String iconUrl = "dog.jpg";
//        model.addAttribute("iconUrl", iconUrl);
////        model.addAttribute("petOwner", petOwner);
//        model.addAttribute("petOwnerId", petOwnerId);
//        model.addAttribute("petOwnerList", petOwnerList);
//        model.addAttribute("pet", pet);
//        return "NewPet";
//    }
    
    // Edit Customer
    @RequestMapping("/editCustomer")
    public String EditCustomer(Model model){
         // Set Page Title
        String pageTitle = "Customers";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "dog.jpg";
        model.addAttribute("iconUrl", iconUrl);
        return "EditCustomer";
    }
}
