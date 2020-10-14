/*
 * This is the SalariesRepository Interface
   The SalariesService Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

//Imports
import com.pg.pet_grooming.Models.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, Integer> {

}

