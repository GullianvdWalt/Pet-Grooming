/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Models.Income;
import com.pg.pet_grooming.Models.Invoice;
import com.pg.pet_grooming.Repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
    
    @Autowired private IncomeRepository incomeRepository;
    
        public Page<Income> getIncome(int pageNum, String sortField, String sortDir) {
        //Paging
        Pageable pageable = PageRequest.of(pageNum - 1, 4, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
        return incomeRepository.findAll(pageable);
    }
    
    public void saveIncome(Income income){
        incomeRepository.save(income);
    }
}
