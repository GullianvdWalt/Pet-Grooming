/*
   Created By Gullian Van Der Walt 01/08/2020
 * Last Update - 2020/09/18, 10:27
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.util.*;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.AppointmentsService;
import com.pg.pet_grooming.Services.PetService;
import com.pg.pet_grooming.Services.ServicesService;
import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Models.Appointments_Pets;
import com.pg.pet_grooming.Repositories.AppointmentRepository;
import com.pg.pet_grooming.Repositories.Appointments_Pet_Services_Repo;
import com.pg.pet_grooming.Services.AppointmentsPetsService;
import com.pg.pet_grooming.Services.Appointments_Pet_Services_Service;
import com.pg.pet_grooming.Models.Appointments_Pet_Services;
import com.pg.pet_grooming.Repositories.ServicesRepository;



@Controller
public class AppointmentController {
    
    // Inject Services and Repos
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private PetService petService;
    @Autowired private ServicesService servicesService;
    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;
    @Autowired private AppointmentsService appointmentService;
    @Autowired private AppointmentRepository appointmentRepository;
    @Autowired private AppointmentsPetsService appointmentsPetsService;
    @Autowired private Appointments_Pet_Services_Service appPetServicesService;
    @Autowired private Appointments_Pet_Services_Repo appPetServicesRepo;
    @Autowired private ServicesRepository servicesRepository;
    
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
    public String newAppointment(Model model,
        @ModelAttribute("petList") List<Pet> petList, Appointments appointment){
       Pet pet = new Pet();
       pet = petList.get(0);
       int petOwnerId = pet.getPet_owner_id();
        try {
            PetOwner petOwner = petOwnerService.findPetOwnerById(petOwnerId);
            model.addAttribute("petOwner", petOwner);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            // add messages
        }
       
       // Set Page Title
       String pageTitle = "New Appointment";
       model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
       String iconUrl = "newAppointment.png";
       model.addAttribute("iconUrl", iconUrl);
       // Get Services
       List<Services> serviceList = servicesService.getServices();
       model.addAttribute("serviceList", serviceList);
       
       model.addAttribute("newAppointment", new Appointments());
       
        return "NewAppointment";
    }
    
    @RequestMapping(value="/newAppointments/new/save", method=RequestMethod.POST)
    public String saveAppointment(Model model,
            @Valid @ModelAttribute("newAppointment") Appointments newAppointment,
            @RequestParam("pet_id") List<Integer> petIds,
            @RequestParam("service_id") List<Integer> serviceIds
            ,BindingResult result){
        
        // Pet - Appointment Join Table object
        Appointments_Pets appointmentsPets = new Appointments_Pets();
        
        // Services - Pet - Appointment Join Table object
        Appointments_Pet_Services appPetServices = new Appointments_Pet_Services();
        
        // Pet Object
        Pet pet = new Pet();
        Pet pet2 = new Pet();
        
        // Services Object
        Services services = new Services();
        
        
         if(result.hasErrors()){
             // Add messages 
             System.out.println(result);
         }else{
             
             // Array List of Pets
             List<Pet> petList = new ArrayList<>();
             List<Pet> petList2 = new ArrayList<>();
             
             
             // Array List of Services
             List<Services> serviceList = new ArrayList<>();
             // Array List of appointments
             List<Appointments> appointmentList = new ArrayList<>();
             
             if(petIds.size() > 1){
                 
               // Loop for total of pet Ids
               for (int i = 0; i < petIds.size(); i++) {

                   // Save each pet id
                   int petId = petIds.get(i);

                   // get pet
                   pet = petRepository.getOne(petId);
                                     
                   
                   // add pet to array list
                   petList.add(pet);
                      
                    pet2 = petRepository.getOne(petId);
                        
                     petList2.add(pet2);

//                    appPetServices.setPet(pet2);

                        
                    // add pet array list to newAppointment
                    newAppointment.setPetList(petList);
                   
                    // add new appointment to joint table
                    appointmentsPets.setAppointment(newAppointment);

                    appointmentList.add(newAppointment);

                      // save joint table Appointments_Pets
                    appointmentsPetsService.createRealationship(appointmentsPets); 
                    
//                    services.setPet(petList);

               }
                   for (int j = 0; j < serviceIds.size(); j++) {
                       int serviceId = serviceIds.get(j);
                       
                       services = servicesRepository.getOne(serviceId);
                       
                       serviceList.add(services);
                       
                       
                       for (int i = 0; i < petIds.size(); i++) {
                           int petId = petIds.get(i);
//                           pet2 = petRepository.getOne(petId);
//                           petList2.add(pet2);
//                         services.setPet(petList2);
//                           services.setAppointment(appointmentList);
                           newAppointment.setServices(serviceList);
                           appPetServices.setAppointment(newAppointment);
                           appPetServicesService.createRelationship(appPetServices);                     

                       }


  
                       
                   }

               // Only one Pet Appointment
             }else{
                   int petId = petIds.get(0);
                    // get pet
                   pet = petRepository.getOne(petId);
                   // add pet to array list
                   petList.add(pet);
                   // add pet array list to newAppointment
//                   newAppointment.setPetList(petList);
                   // add new appointment to joint table
                   appointmentsPets.setPet(pet);
                   appointmentsPets.setAppointment(newAppointment);
                   // save appointment
                   appointmentService.saveAppointment(newAppointment);
                   // save joint table
                   appointmentsPetsService.createRealationship(appointmentsPets);
             }

            
         }      

        return "redirect:/";

    }


    
    // Appointment Complete
    @RequestMapping(value="/appointmentComplete", method=RequestMethod.POST)
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
