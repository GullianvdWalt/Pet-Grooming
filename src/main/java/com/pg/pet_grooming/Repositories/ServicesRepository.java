/*
 * This is the ServicesRepository Interface
   The ServicesService Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

// Imports
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
// Local Imports
import com.pg.pet_grooming.Models.Services;


@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer>{
    
}
