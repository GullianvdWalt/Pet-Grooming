/*
 * This is the AppointmentServiceRepository Interface
   The AppointmentServices_Service Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

// Imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Local Imports
import com.pg.pet_grooming.Models.Appointment_Services;

@Repository
public interface AppointmentServiceRepository extends JpaRepository<Appointment_Services, Integer>{
    
}
