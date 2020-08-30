/*
 *  This is the CashCount Repository 
 *  The CashCount Service Class will implement this interface.
 *  This interface will extend the JpaRepository interface.
 *  This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

//Imports
import com.pg.pet_grooming.Models.Cash_Count;
import org.springframework.data.jpa.repository.JpaRepository;

// Local Imports
import org.springframework.stereotype.Repository;

@Repository
public interface CashCountRepository extends JpaRepository<Cash_Count, Integer>{
    
}
