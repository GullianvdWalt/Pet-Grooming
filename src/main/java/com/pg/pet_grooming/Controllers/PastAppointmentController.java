/*
    Created By Gullian Van Der Walt - 2020/10/13, 06:27
 */
package com.pg.pet_grooming.Controllers;

// Imports
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// Local Imports
import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Models.PastAppointments;
import com.pg.pet_grooming.Models.Past_Appointments_Pet_Services;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Repositories.ServicesRepository;
import com.pg.pet_grooming.Services.AppointmentsService;
import com.pg.pet_grooming.Services.PastAppointmentService;
import com.pg.pet_grooming.Services.Past_Appointments_Pet_Services_Service;

@Controller
public class PastAppointmentController {
 
    @Autowired private PastAppointmentService pastAppService;
    @Autowired private AppointmentsService appService;
    @Autowired private PetRepository petRepository;
    @Autowired private ServicesRepository servicesRepository;
    @Autowired private Past_Appointments_Pet_Services_Service pastAppPetServicesService;
    
        // Save To Past Appointment an Delete In Appointments
    @RequestMapping(value="/appointmentComplete/save/delete", 
        method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE})
    public String appSaveAndDelete(Model model,
            @Valid @ModelAttribute("pastAppointment") Appointments newAppointment,
            @RequestParam("pet_id") int petId,
            @RequestParam("service_id") List<Integer> serviceIds,
            @RequestParam("app_id")Integer id,
            @RequestParam("app_date_time") String date_time,
            PastAppointments pastAppointment) throws ParseException{
        
         // Services - Pet - Appointment Join Table object
        Past_Appointments_Pet_Services pastAppPetServices = new Past_Appointments_Pet_Services();       
        
        // Pet Object
        Pet pet = new Pet();;
        
        // Services Object
        Services services = new Services();
                  
        // Array List of Services
        List<Services> serviceList = new ArrayList<>();
        // Array List of appointments
        List<Appointments> appointmentList = new ArrayList<>();
             
        // Get Pet
        pet = petRepository.getOne(petId);
        pastAppointment.setPet(pet);

        // Convert String To Date
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ROOT);
        Date date = (Date)dateTimeFormat.parse(date_time);

        pastAppointment.setApp_date_time(date);
        

        // Find Services      
        serviceList = servicesRepository.findAllById(serviceIds);
        // Set Services
        pastAppointment.setServices(serviceList);
        // Save
        pastAppPetServicesService.createRelationship(pastAppPetServices);   
        
        pastAppService.savePastAppointment(pastAppointment);
        appService.deleteAppointment(id);
        
        return "redirect:/invoice/new/{id}";
    }
    
}
