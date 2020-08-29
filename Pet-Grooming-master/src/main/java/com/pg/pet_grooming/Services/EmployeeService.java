/*
 * This is the Employee Service Class
 * This Class implements the EmployeeReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.pet_grooming.Repositories.EmployeeRepository;

@Service
public class EmployeeService {  
    
    // Inject EmployeeRepository
    @Autowired
    private EmployeeRepository employeeRepository;
}
