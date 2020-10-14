/*
    Created By Gullian Van Der Walt 18/09/2020
    Last Updated 2020/09/18, 10:23
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Appointments_Pet_Services;
import com.pg.pet_grooming.Repositories.Appointments_Pet_Services_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Appointments_Pet_Services_Service {

    @Autowired
    private Appointments_Pet_Services_Repo appointmentsPetServicesRepo;

    public Appointments_Pet_Services_Service(Appointments_Pet_Services_Repo appointmentsPetServicesRepo) {
        this.appointmentsPetServicesRepo = appointmentsPetServicesRepo;
    }

    // Save
    public Appointments_Pet_Services createRelationship(Appointments_Pet_Services aps) {
        return appointmentsPetServicesRepo.save(aps);
    }

}

