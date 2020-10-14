/*
 * 
 *  This is the Employee Repository 
    The Employee Service Class will implement this interface.
 *  This interface will extend the JpaRepository interface.
    This will provide services such as CRUD operations

 */
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.Models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {

}
