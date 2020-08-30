/*
 * This is the FinanceController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class FinanceController {    
    
    @GetMapping("/finance")
    public String getFiance(){
        return "Finance";
    }     
    
}
