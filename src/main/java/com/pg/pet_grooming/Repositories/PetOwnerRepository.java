/*
 *  This is the Pet Owner Repository 
 *  The PetOwner Service Class will implement this interface.
 *  This interface will extend the JpaRepository interface.
 *  This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

// Imports
import com.pg.pet_grooming.Models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pg.pet_grooming.Models.PetOwner;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// Local Imports
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer>{
            // SQL Query
    	@Query(value="SELECT * FROM pet_owner p WHERE p.pet_owner_full_name LIKE %:keyword% "
                + "OR p.pet_owner_address LIKE %:keyword% OR p.pet_owner_cell LIKE %:keyword% ", nativeQuery=true)
	List<PetOwner> findByKeyword(@Param("keyword") String keyword);
}
