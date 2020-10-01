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
import com.pg.pet_grooming.Repositories.EmployeeRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class EmployeeController {
    
    // Inject EmployeeService
    @Autowired private EmployeeService employeeService;
    @Autowired private EmployeeRepository employeeRepository;
    
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
      model.addAttribute("employeeList",employeeList);
        return "Employees";
    }
    
    // Employee Details (Edit)
    @RequestMapping("/employees/details/{id}")
    public String getEmployees(@PathVariable("id") Integer id, 
    @Valid Employees employee,BindingResult result,Model model)
    throws ResourceNotFoundException{
             
         // Set Page Title
      String pageTitle = "Edit Employee";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "employee.png";
      model.addAttribute("iconUrl", iconUrl);
      
      if(!id.equals(null)){
        employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id: " + id));
        model.addAttribute("employee", employee);
      }else{
            return"redirect:/employees";
        }
        return "EmployeeDetails";
    }
    
    @RequestMapping(value="/employee/update",method= {RequestMethod.POST})
    public String updateEmployee(@Valid Employees employee,BindingResult result, 
            RedirectAttributes redirAttrs, Model model){
        
        if(result.hasErrors()){
            return "EmployeeDetails";
        }else{
                 employeeService.saveEmployee(employee);
                 redirAttrs.addFlashAttribute("success", "Employee Updated!");
        }
        

         
         return "redirect:/employees";
    }
    
        // New Employee
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

    // Post and Save Employee
    @PostMapping("/employees/add")
    public String addEmployee(Model model,Employees employee,
            @RequestParam("date_hired") String dateHired,
            @RequestParam("emp_sa_id") String saId,
            BindingResult result, 
            RedirectAttributes redirAttrs) throws ParseException{
    
        // Convert String to Date
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        Date date = (Date)dateTimeFormat.parse(dateHired);
        employee.setDate_hired(date);
        
        // Conver SA ID String to int
        
//        Integer saIdConverted = Integer.parseInt(saId);
//        employee.setEmp_sa_id(saIdConverted);
        
        if(result.hasErrors()){
        
        }else{
            employeeService.saveEmployee(employee);
            redirAttrs.addFlashAttribute("success", "Employee Added!");
        }
         
       return "redirect:/employees";
    }
    
        // Delete Employee
    @RequestMapping(value="/employee/delete", method={RequestMethod.DELETE,RequestMethod.GET})
    public String deletePet(Integer id,RedirectAttributes redirAttrs){
        //, @PathVariable("pet_owner_id")int petOwnerId
        employeeService.deleteEmployee(id);
        redirAttrs.addFlashAttribute("success", "Employee has been deleted!");
        return "redirect:/employees";
    }
}
