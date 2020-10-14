/*
 * This is the ServicesController Class
 * This class will handle the CRUD operations and Thymeleaf.
 */
package com.pg.pet_grooming.Controllers;

//Imports
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.ServicesRepository;
import com.pg.pet_grooming.Services.ServicesService;
import com.pg.pet_grooming.Utilities.FileUploadUtil;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ServicesDetailsConroller {

    // Inject Service
    @Autowired
    ServicesService servicesService;
    @Autowired
    ServicesRepository servicesRepository;

    @RequestMapping("/services/{id}")
    public String getService(Model model, @PathVariable("id") Integer id) {
        Services service = new Services();
        service = servicesRepository.getOne(id);
        model.addAttribute("service", service);
        String pageTitle = "Edit Services";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "servicesSmall.png";
        model.addAttribute("iconUrl", iconUrl);
        return "EditServices";
    }

    // Update Service
    @RequestMapping(value = "/updateService", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
    public String updateService(Model model, Services service, RedirectAttributes redirAttrs,
            @RequestParam("image") MultipartFile multipartFile,
            BindingResult result) throws IOException {

        if (result.hasErrors()) {
            redirAttrs.addFlashAttribute("error", "There was an error..");
        }

        if (multipartFile.isEmpty()) {
            String imagePath = service.getService_icon();
            service.setService_icon(imagePath);
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        service.setService_icon(fileName);

        servicesService.saveService(service);

        String uploadDir = "/Icon/" + service.getService_id();
        // Use Utility class to save file to directory
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        redirAttrs.addFlashAttribute("success", "Service updated!");
        return "redirect:/manageBusiness";
    }

    // New Service Page Get
    @RequestMapping("/service/new")
    public String newService(Model model) {

        // Set Page Title
        String pageTitle = "New Service";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "servicesSmall.png";
        model.addAttribute("iconUrl", iconUrl);
        return "NewService";
    }

    // Add Service
    @RequestMapping("/addService")
    public String addService(Services service,
            RedirectAttributes redirAttrs, BindingResult result,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (result.hasErrors()) {
            redirAttrs.addFlashAttribute("error", "There was an error..");
        }

        if (multipartFile.isEmpty()) {
            String imagePath = service.getService_icon();
            service.setService_icon(imagePath);
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        service.setService_icon(fileName);

        servicesService.saveService(service);

        String uploadDir = "/Icon/" + service.getService_id();
        // Use Utility class to save file to directory
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        redirAttrs.addFlashAttribute("success", "Service Added!");
        return "redirect:/manageBusiness";
    }

    // Delete Service
    @RequestMapping(value = "/service/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteService(@PathVariable("id") Integer id, RedirectAttributes redirAttrs) {
        //, @PathVariable("pet_owner_id")int petOwnerId
        servicesService.deleteService(id);
        redirAttrs.addFlashAttribute("success", "Service has been deleted!");
        return "redirect:/manageBusiness";
    }
}

