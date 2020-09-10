/*
    Created By Gullian Van Der Walt - 2020/09/10, 15:15
    Last Updated - 2020/09/10, 15:16
 */
package com.pg.pet_grooming.Repositories;

// Imports
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// Local Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.DTO.PetOwnerPet;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer>{
            // SQL Query
    	@Query(value="SELECT * FROM pet_owner p WHERE p.pet_owner_full_name LIKE %:keyword% "
                + "OR p.pet_owner_address LIKE %:keyword% OR p.pet_owner_cell LIKE %:keyword% ", nativeQuery=true)
	List<PetOwner> findByKeyword(@Param("keyword") String keyword);
        
        @Query(value = "SELECT * FROM pet_owner WHERE id LIKE %:id%", nativeQuery=true)
        List<PetOwner>findPetOwnerById(Integer id);
        
        
        @Query("SELECT new com.pg.pet_grooming.DTO.PetOwnerPet(p.id,p.pet_owner_full_name,p.pet_owner_cell,p.pet_owner_address,e.pet_name,e.pet_breed)FROM PetOwner p JOIN p.pets e")
        List<PetOwnerPet> customerList();
}
