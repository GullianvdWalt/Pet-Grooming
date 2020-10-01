/*
 *  This is the Employee Repository 
 *   The Employee Service Class will implement this interface.
 *  This interface will extend the JpaRepository interface.
 *   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

// Imports
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// Local Imports
import com.pg.pet_grooming.Models.Employees;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer>{
        // SQL Query
    	@Query(value="SELECT * FROM Employee e where e.employee_full_name LIKE %:keyword% "
                + "OR e.occupation LIKE %:keyword% OR e.date_hired LIKE %:keyword% "
                + "OR e.cell LIKE %:keyword%", nativeQuery=true)
	List<Employees> findByKeyword(@Param("keyword") String keyword);
        
        @Query(value = "SELECT * FROM employee WHERE id LIKE %:id%", nativeQuery=true)
        List<Employees>findEmployeeById(Integer id);
}
