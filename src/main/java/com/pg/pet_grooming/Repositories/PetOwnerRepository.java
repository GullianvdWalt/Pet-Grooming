/*
 * 
 *  This is the Pet Owner Repository 
    The Pet Service Class will implment this interface.
 *  This interface will extend the JpaRepository interface.
    This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pg.pet_grooming.Models.PetOwner;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long>{
    
}
