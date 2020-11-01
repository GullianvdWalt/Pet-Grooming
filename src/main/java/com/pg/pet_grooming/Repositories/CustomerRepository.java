/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Repositories;

// Imports
import com.pg.pet_grooming.Models.PetOwner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<PetOwner, Integer> {
    // SQL Query

    @Query(value = "SELECT * FROM Customer e where e.pet_owner_full_name LIKE %:keyword% "
            + "OR e.pet_owner_cell LIKE %:keyword% OR e.pet_owner_address LIKE %:keyword% "
            + "OR e.pet_owner_city LIKE %:keyword%", nativeQuery = true)
    List<PetOwner> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM employee WHERE id LIKE %:id%", nativeQuery = true)
    List<PetOwner> findCustomerById(Integer id);
}


