/*
 * This is the AppointmentsService Class
 * This Class implements the AppointmentReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Local Imports
import com.pg.pet_grooming.Repositories.AppointmentRepository;
import com.pg.pet_grooming.Models.Appointments;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {

// Inject Repository    
@Autowired private AppointmentRepository appointmentRepo;

    // Return Appointments
    public List<Appointments> getAppointments(){
        
        return appointmentRepo.findAll();
    }
    
    // Save New Appointment
    public void saveAppointment(Appointments appointment){
        appointmentRepo.save(appointment);
    }
    
    // Get Appointment by ID
    public Optional<Appointments>findAppById(int id){
        return appointmentRepo.findById(id);
    }
    
    // Delete Appointment
    public void deleteAppointment(Integer id){
        appointmentRepo.deleteById(id);
    }
}
