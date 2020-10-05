/*
  Created By - Gullian Van Der Walt

 * This is the ServicesService Class
 * This Class implements the ServiceReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Local Imports
import com.pg.pet_grooming.Repositories.ServicesRepository;
import com.pg.pet_grooming.Models.Services;
import java.util.Optional;

@Service
public class ServicesService {

    // Inject Repository 
    @Autowired private ServicesRepository serviceRepository;    
    
    // Return Service
    public List<Services> getServices(){
        return serviceRepository.findAll();
    }
    
    // Save New Service
    public void saveService(Services service){
        serviceRepository.save(service);
    }
    
    // Get Service
    public Optional<Services> findServiceById(int id){
        return serviceRepository.findById(id);
    }
    
    // Delete Service
    public void deleteService(Integer id){
        serviceRepository.deleteById(id);
    }

}
