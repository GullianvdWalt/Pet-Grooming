/*
 * This is the EmployeeService Class
 * This Class implements the EmployeeReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
// Local Imports
import com.pg.pet_grooming.Models.Employees;
import com.pg.pet_grooming.Repositories.EmployeeRepository;

@Service
public class EmployeeService {  
    
    // Inject EmployeeRepository
    @Autowired private EmployeeRepository employeeRepository;
    
        // Return Employees
    public List<Employees> getEmployees(){
        return employeeRepository.findAll();
    }
    
    // Save New Emmployee
    public void saveEmployee(Employees employee){
        employeeRepository.save(employee);
    }
    
    // Get Employee By ID
    public List<Employees> findEmployeeById(Integer id){
        return employeeRepository.findEmployeeById(id);
    }
    
    
    
    // Delete Employee
    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }
    
    // Get Employee By Keyword from SQL Query Defined in the EmployeeRepository
   public List<Employees> findEmployeeByKeyword(String keyword){
       return employeeRepository.findByKeyword(keyword);
   }
}
