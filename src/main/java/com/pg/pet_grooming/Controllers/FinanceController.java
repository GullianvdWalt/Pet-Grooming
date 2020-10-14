/*
   Created By Gullian Van Der Walt

 * This is the FinanceController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinanceController {

    // Main Finance Page
    @RequestMapping("/finance")
    public String showFinance(Model model) {
        // Set Page Title
        String pageTitle = "Finance";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "finance.png";
        model.addAttribute("iconUrl", iconUrl);
        return "Finance";
    }

}

