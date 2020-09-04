/*
 * Created By Gullian Van Der Walt
 * 04-09-2020
 * 
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
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ErrorControllerCustom implements ErrorController{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorControllerCustom.class);
    
    @GetMapping("/error")
    public String handleError(HttpServletRequest request){
        String errorPage = "error";
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        
        if(status!= null){
            Integer statusCode = Integer.valueOf(status.toString());
            
            if(statusCode == HttpStatus.NOT_FOUND.value()){
                // Handle 404 Error
                errorPage = "error/404";
                LOGGER.error("Error 404");
            } else if(statusCode == HttpStatus.FORBIDDEN.value()){
                // Handle 403 Error
                errorPage = "error/403";
                LOGGER.error("Error 403");
                
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                // Hanlde 500 Internal Server Error
                errorPage = "error/500";  
                LOGGER.error("Error 500");
            }   
        }
        return errorPage;
    }
    
     
    @Override
    public String getErrorPath(){
        return "/error";
    }
 
}
