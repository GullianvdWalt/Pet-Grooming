/*
 * This is the Employee Controller Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
// Local Imports
import com.pg.pet_grooming.Services.EmployeeService;
import com.pg.pet_grooming.Models.Employees;




@Controller
public class EmployeeController {
    
    // Inject EmployeeService
    @Autowired private EmployeeService employeeService;
    
    
    @GetMapping("/employees")
    public String getEmployees(Model model){
        List<Employees> employeeList = employeeService.getEmployees();
        return "Employees";
    } 
}
