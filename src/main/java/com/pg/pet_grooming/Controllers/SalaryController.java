/*
 * This is the SalaryController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import com.pg.pet_grooming.Models.Invoice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
// Local Imports
import com.pg.pet_grooming.Services.SalariesService;
import com.pg.pet_grooming.Models.Salaries;

public class SalaryController {
   
    // Inject Service
    @Autowired SalariesService salaryService;
    
    @GetMapping("/salaries")
    public String getSalaries(Model model){
        List<Salaries> salaryList = salaryService.getSalaries();
        return "Salaries";
    }
}
