/*
   Created By Gullian Van Der Walt
 * This is the SalaryController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
// Local Imports
import com.pg.pet_grooming.Services.SalariesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SalaryController {
   
    // Inject Service
    @Autowired SalariesService salaryService;
    
    @RequestMapping("/finance/salaries")
    public String getSalaries(Model model){
      // Set Page Title
      String pageTitle = "Salaries";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "payroll.png";
      model.addAttribute("iconUrl", iconUrl);
      return "Salaries";
    } 
}
