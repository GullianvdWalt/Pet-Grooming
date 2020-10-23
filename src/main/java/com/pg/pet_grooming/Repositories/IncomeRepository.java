/*
    Created By Gullian Van Der Walt
 */
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.Models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer>{
    
}
