/*
 * 
 *  This is the Pet Repository 
    The Pet Service Class will implement this interface.
 *  This interface will extend the JpaRepository interface.
    This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

// Imports
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Local Imports
import com.pg.pet_grooming.Models.Pet;
import java.util.Optional;


@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{

        // SQL Query
    	@Query(value="SELECT * FROM pet p where p.pet_name LIKE %:keyword% "
                + "OR p.pet_breed LIKE %:keyword% OR p.pet_gender LIKE %:keyword% "
                + "OR p.pet_size LIKE %:keyword%", nativeQuery=true)
	List<Pet> findByKeyword(@Param("keyword") String keyword);
        
        List<Pet> findByPetOwnerId(int pet_owner_id);
        Optional<Pet> findByIdAndPetOwnerId(Integer id, Integer pet_owner_id);
        
//        @Query(value="SELECT pet_name FROM pet WHERE id =:ID ORDER BY id",nativeQuery=true)
//        public String getPetName(@Param("ID") int pId);
        
        @Query(value="SELECT id,created_by,last_modified_by,last_modified_date,pet_name,pet_breed,pet_gender,pet_size,pet_notes,pet_owner_id FROM pet WHERE id =:ID",nativeQuery=true)
        public List<Pet> getPetByPetID(int ID);
        
        @Query(value="SELECT * FROM pet p WHERE p.pet_owner_id =:petOwnerId",nativeQuery=true)
        List<Pet>getPetByPetOwnerId(int petOwnerId);
}
