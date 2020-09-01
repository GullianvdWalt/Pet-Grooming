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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppointmentController {
    
    // New Appointment Page - Select Customer
    @RequestMapping("/newAppointments/select")
    public String newAppointmentSelect(Model model){
      // Set Page Title
      String pageTitle = "Customers";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "dog.jpg";
      model.addAttribute("iconUrl", iconUrl);
        return "SelectCustomer";
    }
    
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
