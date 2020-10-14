/*
    Created By Gullian Van Der Walt 18/09/2020
    Last Updated 2020/09/18, 10:23
 */
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Local Imports
import com.pg.pet_grooming.Models.Past_Appointments_Pet_Services;
import com.pg.pet_grooming.Repositories.Past_Appointments_Pet_Services_Repo;

@Service
public class Past_Appointments_Pet_Services_Service {
    
   @Autowired private Past_Appointments_Pet_Services_Repo pastAppPetServicesRepo;

    public Past_Appointments_Pet_Services_Service(Past_Appointments_Pet_Services_Repo pastAppPetServicesRepo) {
        this.pastAppPetServicesRepo = pastAppPetServicesRepo;
    }

    // Save
    public Past_Appointments_Pet_Services createRelationship(Past_Appointments_Pet_Services paps){
        return pastAppPetServicesRepo.save(paps);
    }
   
    
}
