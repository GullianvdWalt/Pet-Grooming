/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*

    This is the main class for the main pet grooming application
    This Is The Main Application Controller Class
*/


package com.pg.pet_grooming;

// Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {


   // Method To Return index.html
    @GetMapping("/index")
    public String goHome(){
        return "index";
    }
    // Set Page Title and Icon
    @RequestMapping()
    public String title(Model model){
      // Set Page Title
      String pageTitle = "Dashboard";
      model.addAttribute("pageTitle", pageTitle);
      String iconUrl = "dogHouse.png";
      model.addAttribute("iconUrl", iconUrl);
      return "index";
    }

    
    // Method To Login
    @GetMapping("/login")
    public String login(){
        return "Login";
    }
    // Method To Logout
    @GetMapping("/logout")
    public String logout(){
        return "Login";
    }
    
     // Method To Logout
    @GetMapping("/register")
    public String register(){
        return "Register";
    }
}
