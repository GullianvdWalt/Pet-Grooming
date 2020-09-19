/*
    Created By Gullian Van Der Walt 18/09/2020
    Last Updated 2020/09/18, 10:23
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Appointments_Pets;
import com.pg.pet_grooming.Repositories.AppointmentsPetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentsPetsService {
    
    @Autowired private AppointmentsPetsRepository appointmentPetsRepo;

    public AppointmentsPetsService(AppointmentsPetsRepository appointmentPetsRepo) {
        this.appointmentPetsRepo = appointmentPetsRepo;
    }
    
    // Save
    public Appointments_Pets createRealationship(Appointments_Pets ap){
        return this.appointmentPetsRepo.save(ap);
    }
}
