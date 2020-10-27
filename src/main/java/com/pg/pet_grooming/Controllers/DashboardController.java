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
import com.pg.pet_grooming.Services.AppointmentsService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    // Inject Services / Repositories
    @Autowired AppointmentRepository appointmentRepository;
    @Autowired Appointments_Pet_Services_Repo app_pet_ser_repo;
    @Autowired PetRepository petRepository;
    @Autowired AppointmentsService appointmentsService;

    
        // New Appointment Page - Select Pet
    @RequestMapping("/")
    public String appointmentsPage(Model model){

        return viewPage(model, 1, "appDateTime", "asc");

    }
    
    
    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
            @Valid @PathVariable(name = "pageNum") int pageNum,
            @Valid @Param("sortField") String sortField,
            @Valid @Param("sortDir")String sortDir) {

        // Appointments
        Page<Appointments> page = appointmentsService.getAppointments(pageNum, sortField, sortDir);
        List<Appointments> appointmentList = page.getContent();
        model.addAttribute("appointmentList", appointmentList);
        // Add Paging Details
        model.addAttribute("currentPage", pageNum);		
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        // Add Sorting Details
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        // Sort from asc order to desc
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        // Set Page Title
        String pageTitle = "Dashboard";
        model.addAttribute("pageTitle", pageTitle);
        String iconUrl = "dogHouse.png";
        model.addAttribute("iconUrl", iconUrl);

        return "index";
    }

}

