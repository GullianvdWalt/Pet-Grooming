/*
 * Created By Gullian Van Der Walt 04-09-2020
 */
package com.pg.pet_grooming.Controllers;

// Imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IncomeController {

    @RequestMapping("/finance/income")
    public String showIncome(Model model) {
        // Set Page Title
        String pageTitle = "Finance";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "income.png";
        model.addAttribute("iconUrl", iconUrl);
        return "Income";
    }

}

