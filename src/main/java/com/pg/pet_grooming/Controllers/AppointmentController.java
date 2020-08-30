/*
 * This is the Appointment Controller Class
 * This class will handle the CRUD operations and Thymeleaf.
 * 
 */
package com.pg.pet_grooming.Controllers;

//Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppointmentController {
    
    @GetMapping("/newAppointments")
    public String newAppointment(Model model){
        return "NewAppointment";
    }
}
