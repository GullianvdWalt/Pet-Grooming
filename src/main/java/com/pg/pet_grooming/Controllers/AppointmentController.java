/*
   Created By Gullian Van Der Walt 01/08/2020
 * Last Update - 2020/09/14, 05:41
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.PetService;
import com.pg.pet_grooming.Services.ServicesService;






@Controller
public class AppointmentController {
    
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetService petService;
    @Autowired private ServicesService servicesService;
    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;
    
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
    @GetMapping("/newAppointments/select/petOwner/{petOwnerId}")
    public String getPetOwerId(Model model, 
        @PathVariable("petOwnerId") int petOwnerId,@Valid @ModelAttribute("petOwner")PetOwner petOwner)
        throws ResourceNotFoundException{
        Optional<PetOwner> petOwnerList = petOwnerService.findById(petOwnerId);
        model.addAttribute("petOwnerList", petOwnerList);
       // Set Page Title
        String pageTitle = "New Appointment";
        model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
        String iconUrl = "newAppointment.png";
        model.addAttribute("iconUrl", iconUrl);
        return "redirect:/newAppointments/selectPet/"+petOwnerId;
    }
    
    @RequestMapping(value="/newAppointments/selectPet/{petOwnerId}",  method={RequestMethod.POST,RequestMethod.GET})
    public String selectPet(Model model,@PathVariable(value="petOwnerId")int petOwnerId){
       List<Pet> petList = (List<Pet>) petService.findPetByPetOwnerId(petOwnerId);
       // Set Page Title
       String pageTitle = "New Appointment";
       model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
       String iconUrl = "newAppointment.png";
       model.addAttribute("iconUrl", iconUrl);
       model.addAttribute("petList", petList);
       model.addAttribute("petownerId", petOwnerId);
        return "SelectPet";
     }
    
    @RequestMapping(value="/newAppointments/getPet", method={RequestMethod.POST,RequestMethod.GET})
    public String getPet(Model model,@RequestParam("petId") List<Integer> selectedPets,
           RedirectAttributes redirectAttributes){
//        System.out.println(petOwnerId);
        if(selectedPets!= null){
            List<Pet> petList = petRepository.findAllById(selectedPets);
            redirectAttributes.addFlashAttribute("petList", petList);
            for (int i = 0; i < 1; i++) {
                
            }
                
        }
        
       // Set Page Title
       String pageTitle = "New Appointment";
       model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
       String iconUrl = "newAppointment.png";
       model.addAttribute("iconUrl", iconUrl);

       List<Services> serviceList = servicesService.getServices();
       model.addAttribute("serviceList", serviceList);
        
        return "redirect:/newAppointments/new";
    }

    @RequestMapping("/newAppointments/new")
    public String newAppointment(Model model, @ModelAttribute("petList") List<Pet> petList){
       Pet pet = new Pet();
       pet = petList.get(0);
       int petOwnerId = pet.getPet_owner_id();
        System.out.println(petOwnerId);
        try {
            PetOwner petOwner = petOwnerService.findPetOwnerById(petOwnerId);
            model.addAttribute("petOwner", petOwner);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
//       Optional<PetOwner> petOwnerList = petOwnerService.findById(petOwnerId);
//       model.addAttribute("petOwnerList", petOwnerList);
//       System.out.println(petOwnerList);
       
       
       
       // Set Page Title
       String pageTitle = "New Appointment";
       model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
       String iconUrl = "newAppointment.png";
       model.addAttribute("iconUrl", iconUrl);
       // Get Services
       List<Services> serviceList = servicesService.getServices();
       model.addAttribute("serviceList", serviceList);
        
        return "NewAppointment";
    }
    
    
//       // Return Pets by selected owner
//    @RequestMapping(value="/newAppointments/getPet", method=RequestMethod.POST)
//    public String getPet(@ModelAttribute Pet pet,Model model){
//
//            
//
//
////            model.addAttribute("petOwnerList", petOwnerList);
////            model.addAttribute("petOwnerList", petOwnerList); 
//           return "NewAppointment";
//    }


    
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
