/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Employees;
import com.pg.pet_grooming.Repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    // Inject EmployeeRepository
    @Autowired
    private EmployeeRepository employeeRepository;

    // Return Employees
    public List<Employees> getEmployees() {
        return employeeRepository.findAll();
    }

    // Save New Emmployee
    public void saveEmployee(Employees employee) {
        employeeRepository.save(employee);
    }

    // Get Employee By ID
    public List<Employees> findEmployeeById(Integer id) {
        return employeeRepository.findEmployeeById(id);
    }

    public Employees getEmployeeById(Integer id) {
        return employeeRepository.getOne(id);
    }

    // Delete Employee
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    // Get Employee By Keyword from SQL Query Defined in the EmployeeRepository
    public List<Employees> findEmployeeByKeyword(String keyword) {
        return employeeRepository.findByKeyword(keyword);
    }
}

