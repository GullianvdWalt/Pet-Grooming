/*
   Created By Gullian Van Der Walt 01/08/2020
 * Last Update - 2020/09/18, 10:27
 */
package com.pg.pet_grooming.Controllers;

//Imports
import com.pg.pet_grooming.DTO.Pet_PetOwner;
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
import com.pg.pet_grooming.Repositories.PastAppointmentRepo;
import com.pg.pet_grooming.Repositories.ServicesRepository;
import com.pg.pet_grooming.Services.EmployeeService;






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
    
    // New Appointment Page - Select Pet
    @RequestMapping("/newAppointments/select")
    public String newAppointmentSelect(Model model,Pet_PetOwner pet_petOwner){
      // Set Page Title
      String pageTitle = "New Appointment";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "newAppointment.png";
      model.addAttribute("iconUrl", iconUrl);
      
      List<Pet_PetOwner> selectPetList = petRepository.SelectPetList();
      model.addAttribute("selectPet", selectPetList);
      
      return "SelectPet";
    }
    
   @RequestMapping(value="/newAppointments/new/{pet_Id}", method={RequestMethod.POST,RequestMethod.GET})
    public String getPet(Model model,@PathVariable("pet_Id") int petId, Appointments newAppointment){
    
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
            @RequestParam("app_date_time") String date_time
            ,BindingResult result) throws ParseException{
               
        // Services - Pet - Appointment Join Table object
        Appointments_Pet_Services appPetServices = new Appointments_Pet_Services();
        
        // Pet Object
        Pet pet = new Pet();;
        
        // Services Object
        Services services = new Services();
        
        
         if(result.hasErrors()){
             // Add messages 
             System.out.println(result); 
         }else{
                          
             
             // Array List of Services
             List<Services> serviceList = new ArrayList<>();
             // Array List of appointments
             List<Appointments> appointmentList = new ArrayList<>();
             
             // get pet
             pet = petRepository.getOne(petId);
             newAppointment.setPet(pet);

            DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ROOT);
            Date date = (Date)dateTimeFormat.parse(date_time);
            newAppointment.setApp_date_time(date);

             // save appointment
             appointmentService.saveAppointment(newAppointment);
             // Find Services      
             serviceList = servicesRepository.findAllById(serviceIds);
             
             newAppointment.setServices(serviceList);
             appPetServicesService.createRelationship(appPetServices);
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
