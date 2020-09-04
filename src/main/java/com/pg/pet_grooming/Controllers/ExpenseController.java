/*
 * Created By Gullian Van Der Walt
 * 04/09/2020
 * 
 */
package com.pg.pet_grooming.Controllers;

// Imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpenseController {
    
     // Employees
    @RequestMapping("/expenses")
    public String getExpenses(Model model){
      // Set Page Title
      String pageTitle = "Expenses";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "exspense.png";
      model.addAttribute("iconUrl", iconUrl);
        return "Expenses";
    }   
    
}
