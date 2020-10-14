/*
    Created By Gullian Van Der Walt - 2020/10/04, 15:35
 */
package com.pg.pet_grooming.Controllers;

import com.pg.pet_grooming.Models.BusinessDetails;
import com.pg.pet_grooming.Models.SalaryDetails;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Services.BusinessDetailsService;
import com.pg.pet_grooming.Services.SalaryDetailsService;
import com.pg.pet_grooming.Services.ServicesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageBusinessController {

    // Inject Services
    @Autowired
    ServicesService servicesService;
    @Autowired
    BusinessDetailsService businessDetailsService;
    @Autowired
    SalaryDetailsService salaryDetailsService;

    @RequestMapping("/manageBusiness")
    public String manageBusiness(Model model) {

        // Get Details
        List<BusinessDetails> businessDetails = businessDetailsService.getBusinessDetails();
        List<Services> servicesList = servicesService.getServices();
        List<SalaryDetails> salaryDetails = salaryDetailsService.getSalaryDetails();

        // Set Page Title
        String pageTitle = "Manage Business";
        model.addAttribute("pageTitle", pageTitle);
        String iconUrl = "manage.png";
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("servicesList", servicesList);
        model.addAttribute("businessDetails", businessDetails);
        model.addAttribute("salaryDetails", salaryDetails);
        return "ManageBusiness";
    }

}

