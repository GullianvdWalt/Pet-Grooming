/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*

    This is the main class for the main pet grooming application
    This Is The Main Application Controller Class
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.util.*;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Services.PetOwnerService;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Services.AppointmentsService;
import com.pg.pet_grooming.Services.ServicesService;
import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Repositories.AppointmentRepository;
import com.pg.pet_grooming.Services.Appointments_Pet_Services_Service;
import com.pg.pet_grooming.Models.Appointments_Pet_Services;
import com.pg.pet_grooming.Models.Employees;
import com.pg.pet_grooming.Repositories.ServicesRepository;
import com.pg.pet_grooming.Services.EmployeeService;
import com.pg.pet_grooming.Services.PetService;





@Controller
public class AppointmentController {
    
    // Inject Services and Repos
    @Autowired private PetOwnerService petOwnerService;
    @Autowired private ServicesService servicesService;
    @Autowired private PetRepository petRepository;
    @Autowired private AppointmentsService appointmentService;
    @Autowired private AppointmentRepository appointmentRepository;
    @Autowired private Appointments_Pet_Services_Service appPetServicesService;
    @Autowired private ServicesRepository servicesRepository;
    @Autowired private EmployeeService employeeService;
    @Autowired private PetService petService;
    
    
    // New Appointment Page - Select Pet
    @RequestMapping("/newAppointments/select")
    public String newAppointmentSelect(Model model){

        return viewPage(model, "", 1, "petOwnerFullName", "asc");

    }
    
    
    // Paging
    @RequestMapping("/newAppointments/select/page/{pageNum}")
    public String viewPage(Model model,
            @Param("keyword") String keyword,
            @Valid @PathVariable(name = "pageNum") int pageNum,
            @Valid @Param("sortField") String sortField,
            @Valid @Param("sortDir")String sortDir){
        
        // Set Page Title
        String pageTitle = "New Appointment";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "newAppointment.png";
        model.addAttribute("iconUrl", iconUrl);
      
        
        if(keyword == null || keyword == ""){

            Page<PetOwner> page = petOwnerService.getPetPetOwners(pageNum, sortField, sortDir);
            List<PetOwner> customerList = page.getContent();
           
            model.addAttribute("customerList", customerList);
           
            
                     // Add Paging Details
            model.addAttribute("currentPage", pageNum);		
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            // Add Sorting Details
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            // Sort from asc order to desc
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
         
            //model.addAttribute("selectPet", selectPetList);
           
        }else{
            // Search has been made via pet name
            if(searchPet(keyword) == true){
              // Get pet matching keyword with SQL query 
              Pet pet = petService.findPetByKeyword(keyword);
              // Add to a list PetOwner has only list of Pets (Many to one)
              List<Pet> petList = new ArrayList<>();
              petList.add(pet);
             // Get Pet Owner via pet
             PetOwner petOwner = pet.getPetOwner();
             // Set the pet owner pet based on keyword SQL query
             petOwner.setPets(petList);
            // Add pet owner to customer list
            List<PetOwner> customerList = new ArrayList<>();
            customerList.add(petOwner);
            model.addAttribute("customerList", customerList);

          }else{
            // Search is based on pet owner details
            List<PetOwner> customerList = petOwnerService.findPetOwnerByKeyword(keyword);
            model.addAttribute("customerList", customerList);
          }
            
        }
        model.addAttribute("keyword", keyword);
        

      //List<Pet_PetOwner> selectPetList = petRepository.SelectPetList();
      //model.addAttribute("selectPet", selectPetList);
      
      return "SelectPet";
    }
    
    
        // Check if the keyword is pet name
    public boolean searchPet(String keyword){
        // No Search
        if(keyword.equals(null)){
        }

        // Get All Pets
        List<Pet> petList = petService.getPets();
        
        // Loop through all pets and check if keyword was pet name
        for (int i = 0; i < petList.size(); i++) {
            if(petList.get(i).getPet_name().equals(keyword)){
                return true;
            }
        }
        
        return false;
    }
    
   @RequestMapping(value="/newAppointments/new/{id}", method={RequestMethod.POST,RequestMethod.GET})
    public String getPet(Model model,@PathVariable("id") int petId, Appointments newAppointment){
    
            if (petId > 0) {
             Pet pet = petRepository.getOne(petId);
             model.addAttribute("pet", pet);
            try {
                PetOwner petOwner = petOwnerService.findPetOwnerById(pet.getPet_owner_id());
                model.addAttribute("petOwner", petOwner);
            } catch (Exception e) {
                    e.printStackTrace();
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
        
            model.addAttribute("newAppointment", newAppointment);
        
        return "NewAppointment";
    }
    
    @RequestMapping(value="/newAppointments/new/save", method={RequestMethod.POST,RequestMethod.GET})
    public String saveAppointment(Model model,
            @Valid @ModelAttribute("newAppointment") Appointments newAppointment,
            @RequestParam("pet_id") int petId,
            @RequestParam("service_id") List<Integer> serviceIds,
            @RequestParam("app_date_time") String date_time,
            RedirectAttributes redirAttrs
            ,BindingResult result) throws ParseException{
           
        try{
                // Services - Pet - Appointment Join Table object
        Appointments_Pet_Services appPetServices = new Appointments_Pet_Services();
        
        List<Appointments> appointmentsList = new ArrayList<>();
        appointmentsList = appointmentRepository.getAppointments();
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ROOT);
        Date date = (Date)dateTimeFormat.parse(date_time);
       
          
        // Pet Object
        Pet pet = new Pet();;
        
        // Services Object
        Services services = new Services();
        
        
         if(result.hasErrors()){
             // Add messages 
         }else{
                          
             
             // Array List of Services
             List<Services> serviceList = new ArrayList<>();
             // Array List of appointments
             List<Appointments> appointmentList = new ArrayList<>();
             
             // get pet
             pet = petRepository.getOne(petId);
             newAppointment.setPet(pet);

            newAppointment.setAppDateTime(date);

            // Find Services      
            serviceList = servicesRepository.findAllById(serviceIds);
                       
            newAppointment.setServices(serviceList);

            // save appointment
            appointmentService.saveAppointment(newAppointment);

             
            appPetServicesService.createRelationship(appPetServices);

            redirAttrs.addFlashAttribute("success", "Appointment Saved!");
         }    
        }catch(DataIntegrityViolationException ex){
//            
            redirAttrs.addFlashAttribute("error","The selected appointment time has already been taken!");
            return "redirect:/";
        }
            
        return "redirect:/";
    }
    
    // Delete Appointment
    @RequestMapping(value = "/delete/appointment/{id}", method={RequestMethod.GET,RequestMethod.DELETE})
    public String deleteAppointment(Model model,
            @PathVariable("id")Integer id,RedirectAttributes redirAttrs){
    
        appointmentService.deleteAppointment(id);
        
        redirAttrs.addFlashAttribute("success", "Appointment has been deleted!");
        return "redirect:/";
    }
    
    // Appointment Complete
    @RequestMapping(value="/appointmentComplete/{id}", method={RequestMethod.GET,RequestMethod.POST})
    public String appointmentComplete(Model model,@PathVariable("id")Integer id) throws ResourceNotFoundException{
      // Get Appointment by Id from Path variable
      Appointments appointment = appointmentRepository.getOne(id);
      // Add to view
      model.addAttribute("appointment", appointment);
      
      // Get list of services
      List<Services> listServices = appointment.getServices();
      // Add to view
      model.addAttribute("listServices", listServices);
      
      // Get List of Employees(Groomers)
      List<Employees> employeeList = employeeService.getEmployees();
      // Add to view
      model.addAttribute("employeeList", employeeList);
      
      // Get PetOwner
      PetOwner petOwner = petOwnerService.findPetOwnerById(appointment.getPet_owner_id());
      // Add To View
      model.addAttribute("petOwner", petOwner);   
      
      // Get Pet
      Pet pet = petRepository.getOne(appointment.getPet_id());
      // Add to view
      model.addAttribute("pet", pet);
      
      // Set Page Title
      String pageTitle = "Appointment Complete";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "servicesSmall.png";
      model.addAttribute("iconUrl", iconUrl);
      return "AppointmentComplete";
    }
 
}
