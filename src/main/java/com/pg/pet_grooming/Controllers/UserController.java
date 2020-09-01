/*
 * Created By Gullian Van Der Walt
 * 
 *  This is the UserController Class
 */
package com.pg.pet_grooming.Controllers;

// Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
// Local Imports
import com.pg.pet_grooming.Models.UserLogin;
import com.pg.pet_grooming.Services.UserService;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class UserController {
    
    // Inject Service
    @Autowired private UserService userService;
    
    // Register a new user
    @PostMapping(value="users/addNew")
    public RedirectView addNew(UserLogin user, RedirectAttributes redirect){
        userService.saveUser(user);
        // Redirect back to login
        RedirectView redirectView = new RedirectView("/login", true);
        redirect.addFlashAttribute("message", "Registration successfull!, you can now login");
        return redirectView;
    }
}
