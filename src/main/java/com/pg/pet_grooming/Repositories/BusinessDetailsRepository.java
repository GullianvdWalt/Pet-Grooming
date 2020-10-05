/*
   Created By Gullian Van Der Walt

 * This is the BusinessDetailsRepository Interface
   The BusinessDetailsService Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

//Imports
import com.pg.pet_grooming.Models.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessDetailsRepository extends JpaRepository<BusinessDetails, Integer>{
    
}
