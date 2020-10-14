/*
    Created By Gullian Van Der Walt - 2020/10/04, 15:26
 */
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.Models.SalaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryDetailsRepo extends JpaRepository<SalaryDetails, Integer> {

}

