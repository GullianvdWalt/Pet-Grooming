/*
 * This is the Employee Controller Class
 * This class will handle the CRUD operations and Thymeleaf.
 * 
 */
package com.pg.pet_grooming.Controllers;

import com.pg.pet_grooming.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    
    // Inject EmployeeService
    @Autowired
    private EmployeeService employeeService;
}
