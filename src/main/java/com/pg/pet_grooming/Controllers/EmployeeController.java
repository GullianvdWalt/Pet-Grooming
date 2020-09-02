/*
   Created By Gullian Van Der Walt

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
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class EmployeeController {
    
    // Inject EmployeeService
    @Autowired private EmployeeService employeeService;
    
    // Employees
    @RequestMapping("/employees")
    public String showEmployees(Model model){
      // Set Page Title
      String pageTitle = "Employees";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "employee.png";
      model.addAttribute("iconUrl", iconUrl);
      List<Employees> employeeList = employeeService.getEmployees();
      model.addAttribute(employeeList);
        return "Employees";
    }
    
    // Employee Details
    @RequestMapping("/employees/details")
    public String getEmployees(Model model){
      // Set Page Title
      String pageTitle = "Employees";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "employee.png";
      model.addAttribute("iconUrl", iconUrl);
        return "EmployeeDetails";
    }
        // Employee Details
    @RequestMapping("/employees/new")
    public String newEmployee(Model model){
      // Set Page Title
      String pageTitle = "New Employee";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "employee.png";
      model.addAttribute("iconUrl", iconUrl);
        return "NewEmployee";
    }

}
