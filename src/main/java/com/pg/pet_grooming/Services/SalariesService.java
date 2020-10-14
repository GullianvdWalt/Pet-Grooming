/*
 * This is the SalariesService Class
 * This Class implements the SalariesReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Salaries;
import com.pg.pet_grooming.Repositories.SalariesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalariesService {

// Inject Repository 
    @Autowired
    private SalariesRepository salaryRepo;

    public List<Salaries> getSalaries() {
        return salaryRepo.findAll();
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

