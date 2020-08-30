/*
 * This is the EmployeeBenefitsService Class
 * This Class implements the EmployeeBenefitsReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
// Local Imports
import com.pg.pet_grooming.Models.EmployeeBenefits;
import com.pg.pet_grooming.Models.Employees;
import com.pg.pet_grooming.Repositories.EmployeeBenefitsRepository;

@Service
public class EmployeeBenefitsService {
   
// Inject Repository
@Autowired EmployeeBenefitsRepository employeeBenefitsRepository;

    public List<EmployeeBenefits> getEmployeeBenefits(){
        return employeeBenefitsRepository.findAll();
    }
    
    public void save(EmployeeBenefits employeeBenefits){
        employeeBenefitsRepository.save(employeeBenefits);
    }
    
    public Optional<EmployeeBenefits> findEmployeeBenefitById(Integer id){
        return employeeBenefitsRepository.findById(id);
    }
    
    public void delete(Integer id){
        employeeBenefitsRepository.deleteById(id);
    }
    
}
