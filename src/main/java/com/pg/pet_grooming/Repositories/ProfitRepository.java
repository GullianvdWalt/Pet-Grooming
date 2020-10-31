/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.Models.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepository  extends JpaRepository<Profit, Integer>{
    
}
