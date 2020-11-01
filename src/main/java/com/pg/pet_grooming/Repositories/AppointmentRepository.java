/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Repositories;

// Imnports
import com.pg.pet_grooming.Models.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
// Local Imports
import com.pg.pet_grooming.Models.Appointments;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments,Integer>{
    
//SELECT * FROM appointments ORDER BY YEAR(app_date_time),MONTH(app_date_time),day(app_date_time), TIME(app_date_time);
    @Query(value="SELECT * FROM appointments ORDER BY YEAR(app_date_time),MONTH(app_date_time),day(app_date_time), TIME(app_date_time)",nativeQuery = true)
    List<Appointments> getAppointments();
        
}

