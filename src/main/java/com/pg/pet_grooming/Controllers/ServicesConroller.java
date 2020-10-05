/*
 * This is the ServicesController Class
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
import com.pg.pet_grooming.Services.ServicesService;
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.ServicesRepository;
import com.pg.pet_grooming.Utilities.FileUploadUtil;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ServicesConroller {
        
      // Inject Service
      @Autowired ServicesService servicesService;
      @Autowired ServicesRepository servicesRepository;
    
    // Update Service
    @RequestMapping(value="/editService/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String updateService(Model model, RedirectAttributes redirAttrs,
            @RequestParam("logo") MultipartFile multipartFile,
            @PathVariable("id")int id, BindingResult result)throws IOException{
    
        if(result.hasErrors()){
            redirAttrs.addAttribute("error", "There was an error..");
        }
        Services service = new Services();  
       if(id < 0){
           redirAttrs.addAttribute("error", "Service not found!");
       }
       service = servicesRepository.getOne(id);
       model.addAttribute("service", service);
       
       String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
       service.setService_icon(fileName);
       
       servicesService.saveService(service);
       
       String uploadDir = "Icon/" + service.getService_id();
       // Use Utility class to save file to directory
       FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
       
       redirAttrs.addAttribute("success", "Service updated!");
        return "redirect:/manageBusiness";
    }
    
    // Add Service
    @RequestMapping("/addService")
    public String addService(@Valid Services service,RedirectAttributes redirAttrs,BindingResult result){
    
        if(result.hasErrors()){
            redirAttrs.addAttribute("error", "There was an error..");
        }
       servicesService.saveService(service);
       redirAttrs.addAttribute("success", "Service Added!");
        return "redirect:/manageBusiness";
    }
}
