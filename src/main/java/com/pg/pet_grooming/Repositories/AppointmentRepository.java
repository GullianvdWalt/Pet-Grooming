// *  This is the Appointment Repository 
//    The Appointment Service Class will implement this interface.
// *  This interface will extend the JpaRepository interface.
//    This will provide services such as CRUD operations

package com.pg.pet_grooming.Repositories;

// Imnports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Local Imports
import com.pg.pet_grooming.Models.Appointments;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments,Integer>{
    
}
