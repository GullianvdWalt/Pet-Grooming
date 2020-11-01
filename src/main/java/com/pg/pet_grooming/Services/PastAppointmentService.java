/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Models.PastAppointments;
import com.pg.pet_grooming.Repositories.PastAppointmentRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PastAppointmentService {
    
    @Autowired private PastAppointmentRepo pastAppointmentRepo;
    
        // Return Appointments
    public Page<PastAppointments> getAppointments(int pageNum, String sortField, String sortDir){
        
        // Paging
        Pageable pageable = PageRequest.of(pageNum - 1, 10, 
        sortDir.equals("asc") ? Sort.by(sortField).ascending()
            : Sort.by(sortField).descending()
	);
        
        return pastAppointmentRepo.findAll(pageable);
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
