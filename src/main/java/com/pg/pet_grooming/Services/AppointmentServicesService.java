/*
 * This is the ApointmentServicesService Class
 * This Class implements the ApointmentServiceReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;


// Imports
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Local Imports
import com.pg.pet_grooming.Models.Appointment_Services;
import com.pg.pet_grooming.Repositories.AppointmentServiceRepository;

@Service
public class AppointmentServicesService {
    
// Inject Repository 
@Autowired private AppointmentServiceRepository appointmentServiceRepo;    

    // Return App Service
    public List<Appointment_Services> getAppService(){
        return appointmentServiceRepo.findAll();
    }
    
    // Save New App Service
    public void saveAppService(Appointment_Services appService){
        appointmentServiceRepo.save(appService);
    }
    
    // Get App Service
    public Optional<Appointment_Services> findAppServiceById(int id){
        return appointmentServiceRepo.findById(id);
    }
    
    // Delete App Service
    public void deleteAppService(Integer id){
        appointmentServiceRepo.deleteById(id);
    }

}
