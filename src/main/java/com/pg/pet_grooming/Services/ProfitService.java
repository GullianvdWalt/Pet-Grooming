/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
// Local Imports
import com.pg.pet_grooming.Models.Profit;
import com.pg.pet_grooming.Repositories.ProfitRepository;

@Service
public class ProfitService {
  
    @Autowired ProfitRepository profitRepository;
    
    // Return Profit with Paging
    public Page<Profit> getProfit(int pageNum, String sortField, String sortDir){

        Pageable pageable = PageRequest.of(pageNum - 1, 5, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
        
        return profitRepository.findAll(pageable);
    }
    
    
    
}
