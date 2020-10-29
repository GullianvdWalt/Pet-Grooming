/*
 * This is the AppointmentsService Class
 * This Class implements the AppointmentReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Appointments;
import com.pg.pet_grooming.Repositories.AppointmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AppointmentsService {

// Inject Repository    
    @Autowired
    private AppointmentRepository appointmentRepo;

    // Return Appointments with Paging
    public Page<Appointments> getAppointments(int pageNum, String sortField, String sortDir){

        // 10 Appointments per page
        Pageable pageable = PageRequest.of(pageNum - 1, 5, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
        
        return appointmentRepo.findAll(pageable);
    }

    // Save New Appointment
    public void saveAppointment(Appointments appointment) {
        appointmentRepo.save(appointment);
    }

    // Get Appointment by ID
    public Optional<Appointments> findAppById(int id) {
        return appointmentRepo.findById(id);
    }

    // Delete Appointment
    public void deleteAppointment(Integer id) {
        appointmentRepo.deleteById(id);
    }
}

