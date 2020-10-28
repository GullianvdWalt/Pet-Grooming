/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
 */
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.Models.Invoice;
import com.pg.pet_grooming.Models.PastAppointments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PastAppointmentRepo extends JpaRepository<PastAppointments, Integer>{
    
// Query to get total past appointment per customer
// SQL - SELECT COUNT(pet_owner_id) AS totalAppointments FROM past_appointments WHERE pet_owner_id = 1;
    @Query(value="SELECT COUNT(p.pet_owner_id) FROM past_appointments p WHERE p.pet_owner_id =:id",nativeQuery = true)
    public Integer getAppCount(int id);
    
    // Search PastAppointments
    // SQL Search Query
    @Query(value = "SELECT * FROM past_appointments p WHERE p.employee_id LIKE %:keyword% "
            + "OR p.notes LIKE %:keyword% OR p.pet_breed LIKE %:keyword%"
            + "OR p.pet_owner_address LIKE %:keyword% OR p.pet_name LIKE %:keyword%"
            + "OR p.pet_owner_cell LIKE %:keyword% OR p.pet_owner_full_name LIKE %:keyword%"
            + "OR p.pet_owner_city LIKE %:keyword%", nativeQuery = true)
   List<PastAppointments> findByKeyword(@Param("keyword") String keyword);
}
