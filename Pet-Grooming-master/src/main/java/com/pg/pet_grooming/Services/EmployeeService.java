/*
 * This is the Employee Service Class
 * This Class implements the EmployeeReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    // Inject EmployeeRepository
    @Autowired
    private EmployeeRepository employeeRepository;
}
