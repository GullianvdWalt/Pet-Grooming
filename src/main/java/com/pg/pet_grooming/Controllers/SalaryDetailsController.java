/*
    Created By Gullian Van Der Walt - 2020/10/05, 14:19
 */
package com.pg.pet_grooming.Controllers;

import com.pg.pet_grooming.Models.SalaryDetails;
import com.pg.pet_grooming.Repositories.SalaryDetailsRepo;
import com.pg.pet_grooming.Services.SalaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SalaryDetailsController {
    
    @Autowired SalaryDetailsService salaryDetailsService;
    @Autowired SalaryDetailsRepo salaryDetailsRepo;
    
    // Update
    @RequestMapping(value="/salaryDetailsUpdate/{id}", 
            method = {RequestMethod.GET, RequestMethod.POST})
    public String updateSalaryDetails(Model model, @PathVariable("id") Integer id,
            RedirectAttributes redirAttrs, BindingResult result){
        
        
    if(result.hasErrors()){
            redirAttrs.addAttribute("error", "There was an error..");
        }
        
    if(id < 0){
        redirAttrs.addAttribute("error", "Business Details not found!");
    }    
        
    SalaryDetails salaryDetails = new SalaryDetails();
    salaryDetails = salaryDetailsRepo.getOne(id);
    salaryDetailsService.saveSalaryDetails(salaryDetails);
    redirAttrs.addAttribute("success", "Service Added!");
    return "redirect:/manageBusiness";
    }
}
