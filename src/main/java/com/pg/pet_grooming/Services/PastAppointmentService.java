/*
    Created By Gullian Van Der Walt - 2020/10/13, 06:27
 */
package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Models.PastAppointments;
import com.pg.pet_grooming.Repositories.PastAppointmentRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PastAppointmentService {
    
    @Autowired private PastAppointmentRepo pastAppointmentRepo;
    
        // Return Appointments
    public List<PastAppointments> getAppointments(){
        
        return pastAppointmentRepo.findAll();
    }
    
    // Save New Appointment
    public void savePastAppointment(PastAppointments pastAppointment){
        pastAppointmentRepo.save(pastAppointment);
    }
    
    // Get Appointment by ID
    public Optional<PastAppointments>findPastAppById(int id){
        return pastAppointmentRepo.findById(id);
    }
    
    // Delete Appointment
    public void deletePastAppointment(Integer id){
        pastAppointmentRepo.deleteById(id);
    }
}
