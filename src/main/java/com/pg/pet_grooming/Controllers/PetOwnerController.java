/*
 * This is the Pet Owner Controller Class
 * This class will handle the CRUD operations and Thymeleaf.
 * 
 */
package com.pg.pet_grooming.Controllers;

import com.pg.pet_grooming.Services.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PetOwnerController {

    @Autowired
    private PetOwnerService petOwnerService;
    
    
}
