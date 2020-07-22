/*
 * 
 *  This is the Pet Repository 
    The Pet Service Class will implment this interface.
 *  This interface will extend the JpaRepository interface.
    This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pg.pet_grooming.Models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{
    
}
