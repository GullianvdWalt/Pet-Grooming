/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming;

// Imports
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControllerCustom implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorControllerCustom.class);

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        String errorPage = "error";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // Handle 404 Error
                errorPage = "error/404";
                LOGGER.error("Error 404");
                
                // Set Page Title
                String pageTitle = "Error";
                model.addAttribute("pageTitle", pageTitle);
                // Set Page Title Icon
                String iconUrl = "error.png";
                model.addAttribute("iconUrl", iconUrl);
                
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // Handle 403 Error
                errorPage = "error/403";
                LOGGER.error("Error 403");
                
                // Set Page Title
                String pageTitle = "Error";
                model.addAttribute("pageTitle", pageTitle);
                // Set Page Title Icon
                String iconUrl = "error.png";
                model.addAttribute("iconUrl", iconUrl);

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // Hanlde 500 Internal Server Error
                errorPage = "error/500";
                LOGGER.error("Error 500");
                
                // Set Page Title
                String pageTitle = "Error";
                model.addAttribute("pageTitle", pageTitle);
                // Set Page Title Icon
                String iconUrl = "error.png";
                model.addAttribute("iconUrl", iconUrl);
            }
        }
        return errorPage;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}

