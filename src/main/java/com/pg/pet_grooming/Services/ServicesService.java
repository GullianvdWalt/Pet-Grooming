/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Services;
import com.pg.pet_grooming.Repositories.ServicesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {

    // Inject Repository 
    @Autowired
    private ServicesRepository serviceRepository;

    // Return Service
    public List<Services> getServices() {
        return serviceRepository.findAll();
    }

    // Save New Service
    public void saveService(Services service) {
        serviceRepository.save(service);
    }

    // Get Service
    public Optional<Services> findServiceById(int id) {
        return serviceRepository.findById(id);
    }

    // Delete Service
    public void deleteService(Integer id) {
        serviceRepository.deleteById(id);
    }

}

