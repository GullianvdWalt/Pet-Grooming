/*
    Created By Gullian Van Der Walt - 2020/10/05, 14:19
    Last Updated - 2020/10/06, 11:20
 */
package com.pg.pet_grooming.Controllers;

import com.pg.pet_grooming.Models.SalaryDetails;
import com.pg.pet_grooming.Repositories.SalaryDetailsRepo;
import com.pg.pet_grooming.Services.SalaryDetailsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SalaryDetailsController {
    
    @Autowired SalaryDetailsService salaryDetailsService;
    @Autowired SalaryDetailsRepo salaryDetailsRepo;
    
    // Get By Id
    @RequestMapping("/find/salaryDetails/{id}")
    @ResponseBody
    public Optional<SalaryDetails>GetEditSalaryById(@PathVariable("id")Integer id, Model model){
        return salaryDetailsService.getById(id);
    }
    
    // Update
    @RequestMapping(value="/salaryDetailsUpdate", 
            method = {RequestMethod.PUT,RequestMethod.GET})
    public String updateSalaryDetails(Model model, SalaryDetails salaryDetails,
            RedirectAttributes redirAttrs, BindingResult result){
        
        
    if(result.hasErrors()){
            redirAttrs.addFlashAttribute("error", "There was an error..");
        }
        
    salaryDetailsService.saveSalaryDetails(salaryDetails);
    redirAttrs.addFlashAttribute("success", "Salary Details Updated!");
    return "redirect:/manageBusiness";
    }
}
