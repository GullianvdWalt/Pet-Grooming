/*
 * Created By Gullian Van Der Walt - 2020/10/04, 15:09
 * Last Updated - 2020/10/04, 15:09
 */
package com.pg.pet_grooming.Controllers;

// Imports
import java.io.IOException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
// Local Imports
import com.pg.pet_grooming.Models.BusinessDetails;
import com.pg.pet_grooming.Repositories.BusinessDetailsRepository;
import com.pg.pet_grooming.Services.BusinessDetailsService;
import com.pg.pet_grooming.Utilities.FileUploadUtil;
import org.springframework.util.StringUtils;


@Controller
public class BusinessDetailsController {
    
    // Inject Services
    @Autowired BusinessDetailsService businessDetailsService;
    @Autowired BusinessDetailsRepository businessDetailsRepo;
    
    // Update
    @RequestMapping("/updateBusinessDetails/{id}")
    public String updateBusinessDetails(Model model,
            @PathVariable("id") Integer id,@RequestParam("logo") MultipartFile multipartFile,
            RedirectAttributes redirAttrs,BindingResult result)throws IOException{
        
        if(result.hasErrors()){
            redirAttrs.addAttribute("error", "There was an error..");
        }
        
        if(id < 0){
           redirAttrs.addAttribute("error", "Business Details not found!");
       }
                 
       BusinessDetails businessDetails = new BusinessDetails();
       businessDetails = businessDetailsRepo.getOne(id);
       model.addAttribute("businessDetails", businessDetails);
       String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
       businessDetails.setLogo(fileName);
       
       businessDetailsService.saveBusinessDetails(businessDetails);
       
       String uploadDir = "Icon/" + businessDetails.getId();
       // Use Utility class to save file to directory
       FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
       
       redirAttrs.addAttribute("success", "Business Details Updated!");
       return "redirect:/manageBusiness";
    }

    
    
}
