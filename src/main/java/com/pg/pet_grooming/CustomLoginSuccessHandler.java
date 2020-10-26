/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.pet_grooming;

import com.pg.pet_grooming.Models.UserLogin;
import com.pg.pet_grooming.Models.UserLoginPrincipal;
import com.pg.pet_grooming.Services.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Autowired UserService userService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserLoginPrincipal userLoginPrinciple = (UserLoginPrincipal) authentication.getPrincipal();
        UserLogin userLogin = userLoginPrinciple.getUser();
        
        if(userLogin.getFailed_attempt() > 0){
            userService.resetFailedAttempts(userLogin.getUsername());
        }
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
    
}
