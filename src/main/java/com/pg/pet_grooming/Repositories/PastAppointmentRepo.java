/*
 * Created By Gullian Van Der Walt - 2020/10/13, 06:18
 */
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.Models.PastAppointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PastAppointmentRepo extends JpaRepository<PastAppointments, Integer>{
    
// Query to get total past appointment per customer
// SQL - SELECT COUNT(pet_owner_id) AS totalAppointments FROM past_appointments WHERE pet_owner_id = 1;
    @Query(value="SELECT COUNT(p.pet_owner_id) FROM past_appointments p WHERE p.pet_owner_id =:id",nativeQuery = true)
    public Integer getAppCount(int id);
}
