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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    // Method To Return index.html
    @GetMapping("/index")
    public String goHome() {
        return "index";
    }

    // Set Page Title and Icon
    @RequestMapping()
    public String title(Model model) {
        // Set Page Title
        String pageTitle = "Dashboard";
        model.addAttribute("pageTitle", pageTitle);
        String iconUrl = "dogHouse.png";
        model.addAttribute("iconUrl", iconUrl);
        return "index";
    }

    // Method To Login
    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
              return "Login";
        }
        
        return "redirect:/";
    }

    // Method To Logout
    @GetMapping("/logout")
    public String logout() {
        return "Login";
    }

    // Method To Register
    @GetMapping("/register")
    public String register() {
        return "Register";
    }

    
}

