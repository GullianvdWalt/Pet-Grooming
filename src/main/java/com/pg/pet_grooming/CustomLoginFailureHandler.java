/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming;

import com.pg.pet_grooming.Models.UserLogin;
import com.pg.pet_grooming.Services.UserService;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Autowired UserService userService;

    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, 
            HttpServletResponse response, AuthenticationException exception) 
            throws IOException, ServletException {
       
        String username = request.getParameter("username");
        UserLogin user = userService.getUserByUsername(username);
        System.out.println(username);
        if(user != null){
            if(user.isAccount_non_locked()){
                // Subtract by 1, 3rd attempt user will be locked
             if(user.getFailed_attempt() < UserService.MAX_FAILED_ATTEMPTS - 1){
                userService.increaseFailedAttempt(user);
             }else{
                 userService.lock(user);
                 exception = new LockedException("Your Account has been locked due to many failed attempts. "
                         + "Your account will be unlocked in 24 hours.");
             }
            }else if(!user.isAccount_non_locked()){
                if(userService.unlock(user)){
                    exception = new LockedException("Your account has been unlocked. You can now log in.");
                }
            }
        }
        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }    

}
