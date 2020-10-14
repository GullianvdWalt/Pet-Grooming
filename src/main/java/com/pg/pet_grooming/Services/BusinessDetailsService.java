/*
 * This is the BusinessDetailsService Class
 * This Class implements the BusinessDetailsReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.BusinessDetails;
import com.pg.pet_grooming.Repositories.BusinessDetailsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessDetailsService {

//// Inject Repository 
    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

// Get
    public List<BusinessDetails> getBusinessDetails() {
        return businessDetailsRepository.findAll();
    }
// Save

    public void saveBusinessDetails(BusinessDetails businessDetails) {
        businessDetailsRepository.save(businessDetails);
    }
// Find by Id

    public Optional<BusinessDetails> getById(Integer id) {
        return businessDetailsRepository.findById(id);
    }

// Delete
    public void deleteBusinessDetails(BusinessDetails businessDetails) {
        businessDetailsRepository.delete(businessDetails);
    }
}

