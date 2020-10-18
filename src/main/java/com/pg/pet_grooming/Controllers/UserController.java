/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
 */
package com.pg.pet_grooming.Controllers;

// Imports
import com.pg.pet_grooming.Models.UserLogin;
import com.pg.pet_grooming.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    // Inject Service
    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping(value = "users/addNew")
    public RedirectView addNew(UserLogin user, RedirectAttributes redirect) {
        userService.saveUser(user);
        // Redirect back to login
        RedirectView redirectView = new RedirectView("/login", true);
        redirect.addFlashAttribute("message", "Registration successfull!, you can now login");
        return redirectView;
    }
}

