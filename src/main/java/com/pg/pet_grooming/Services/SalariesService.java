/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Salaries;
import com.pg.pet_grooming.Repositories.SalariesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SalariesService {

// Inject Repository 
    @Autowired private SalariesRepository salaryRepo;

    public Page<Salaries> getSalaries(int pageNum, String sortField, String sortDir) {
         // Paging
        Pageable pageable = PageRequest.of(pageNum - 1, 10, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
        
        return salaryRepo.findAll(pageable);
    }

    public void saveSalary(Salaries salary) {
        salaryRepo.save(salary);
    }

    public Optional<Salaries> findById(Integer id) {
        return salaryRepo.findById(id);
    }

    public void delete(Integer id) {
        salaryRepo.deleteById(id);
    }
}

