/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*

    This is the main class for the main pet grooming application
    This Is The Main Application Controller Class
 */
package com.pg.pet_grooming.Controllers;

// Imports
import com.pg.pet_grooming.Models.BusinessDetails;
import com.pg.pet_grooming.Repositories.BusinessDetailsRepository;
import com.pg.pet_grooming.Services.BusinessDetailsService;
import com.pg.pet_grooming.Utilities.FileUploadUtil;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BusinessDetailsController {

    // Inject Services
    @Autowired
    BusinessDetailsService businessDetailsService;
    @Autowired
    BusinessDetailsRepository businessDetailsRepo;

    // Get By Id
    @RequestMapping("/find/businessDetails/{id}")
    @ResponseBody
    public Optional<BusinessDetails> GetEditBusinessById(@PathVariable("id") Integer id, Model model) {
        return businessDetailsService.getById(id);
    }

    // Update Business Details
    @RequestMapping(value = "/updateBusinessDetails", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
    public String updateBusinessDetails(Model model, BusinessDetails businessDetails, BindingResult result, RedirectAttributes redirAttrs,
            @RequestParam("id") Integer id,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (result.hasErrors()) {
            redirAttrs.addAttribute("error", "There was an error..");
        }

        if (id < 0) {
            redirAttrs.addAttribute("error", "Business Details not found!");
        }

        //businessDetails = businessDetailsRepo.getOne(id);
        if (multipartFile.isEmpty()) {
            String imagePath = businessDetails.getLogo();
            businessDetails.setLogo(imagePath);
        }

        model.addAttribute("businessDetails", businessDetails);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        businessDetails.setLogo(fileName);

        businessDetailsService.saveBusinessDetails(businessDetails);

        String uploadDir = "/Icon/" + businessDetails.getId();
        // Use Utility class to save file to directory
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        redirAttrs.addFlashAttribute("success", "Business Details Updated!");
        return "redirect:/manageBusiness";
    }

}

