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


@Controller
public class ApplicationController {
    
   // Method To Return index.html
    @GetMapping("/index")
    public String goHome(){
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
}
