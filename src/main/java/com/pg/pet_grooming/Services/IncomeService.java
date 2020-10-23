/*
    Created By Gullian Van Der Walt
 */
package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Models.Income;
import com.pg.pet_grooming.Repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
    
    @Autowired private IncomeRepository incomeRepository;
    
    
    public void saveIncome(Income income){
        incomeRepository.save(income);
    }
}
