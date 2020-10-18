/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Controllers;

// Imports
import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Models.Appointments_Pet_Services;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.AppointmentRepository;
import com.pg.pet_grooming.Repositories.Appointments_Pet_Services_Repo;
import com.pg.pet_grooming.Repositories.PetRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    // Inject Services / Repositories
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    Appointments_Pet_Services_Repo app_pet_ser_repo;
    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String postCustApp(Model model, Appointments appointments, Services services, Pet pet) {

        // Appointments
        List<Appointments> appointmentList = appointmentRepository.getAppointments();
        // Appointment_Pet_Services
        List<Appointments_Pet_Services> appPetSerList = app_pet_ser_repo.findAll();

        List<Pet> petList = new ArrayList<>();

        model.addAttribute("services", services);

        // Set Page Title
        String pageTitle = "Dashboard";
        model.addAttribute("pageTitle", pageTitle);
        String iconUrl = "dogHouse.png";
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("appointmentList", appointmentList);
        return "index";
    }

}

