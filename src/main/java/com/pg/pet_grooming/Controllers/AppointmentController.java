/*
   Created By Gullian Van Der Walt 01/08/2020
 * Last Update - 2020/09/11, 10:24
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Services.PetService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AppointmentController {
    
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetService petService;
    
    // New Appointment Page - Select Customer
    @RequestMapping("/newAppointments/select")
    public String newAppointmentSelect(Model model,PetOwner petOwner){
      // Set Page Title
      String pageTitle = "New Appointment";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "newAppointment.png";
      model.addAttribute("iconUrl", iconUrl);
      // Get Pet Owners and populate table in Select Customers
      List<PetOwner> petOwnerList = petOwnerService.getPetOwners();
      model.addAttribute("petOwnerList", petOwnerList);
      return "SelectCustomer";
    }
    
    // Return Pets by selected owner
    @RequestMapping("/newAppointments/select/petOwner/{petOwnerId}")
    public String getPetOwerId(Model model, @PathVariable("petOwnerId") int petOwnerId)
    throws ResourceNotFoundException{
        System.out.println(petOwnerId);
       // Set Page Title
        String pageTitle = "New Appointment";
        model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
        String iconUrl = "newAppointment.png";
        model.addAttribute("iconUrl", iconUrl);
        
        List<Pet> petList = petService.findPetByPetOwnerId(petOwnerId);
        model.addAttribute("petList", petList);
        
        return "SelectPet";
    }
    
//        // Return Pets by selected owner
//    @RequestMapping("/newAppointments/getPet/{petOwnerId}")
//    public String getPet(Model model, @PathVariable("petOwnerId") int petOwnerId){
//        
//           List<Pet> petList = petService.findPetByPetOwnerId(petOwnerId);
//           System.out.println(petList);
//           model.addAttribute("petList", petList);
//
//           return"SelectPet";
//    }
    
    
    // Main New Appointment Page
    @RequestMapping("/newAppointments/select/new")
    public String newAppointment(Model model){
      // Set Page Title
      String pageTitle = "New Appointment";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "newAppointment.png";
      model.addAttribute("iconUrl", iconUrl);
        return "NewAppointment";
    }
    
    // Appointment Complete
    @RequestMapping("/appointmentComplete")
    public String appointmentComplete(Model model){
      // Set Page Title
      String pageTitle = "Appointment Complete";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "servicesSmall.png";
      model.addAttribute("iconUrl", iconUrl);
        return "AppointmentComplete";
    }

}
