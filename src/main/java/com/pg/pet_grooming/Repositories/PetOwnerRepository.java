/*
    Created By Gullian Van Der Walt - 2020/09/10, 15:15
 */
package com.pg.pet_grooming.Repositories;

// Imports
import com.pg.pet_grooming.Models.PetOwner;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer> {

    // SQL Search Query
    @Query(value = "SELECT * FROM pet_owner p WHERE p.pet_owner_full_name LIKE %:keyword% "
            + "OR p.pet_owner_address LIKE %:keyword% OR p.pet_owner_cell LIKE %:keyword% ", nativeQuery = true)
    List<PetOwner> findByKeyword(@Param("keyword") String keyword);

//        // SQL Search Query
//    @Query("SELECT p.petOwnerFullName FROM PetOwner p WHERE p.petOwnerFullName LIKE %:keyword%")
//    List<PetOwner> findByKeyword(@Param("keyword") String keyword);
    
  
    // Get PetOwner By Id
    @Query(value = "SELECT * FROM pet_owner WHERE id LIKE %:id%", nativeQuery = true)
    List<PetOwner> findPetOwnerById(Integer id);

}

