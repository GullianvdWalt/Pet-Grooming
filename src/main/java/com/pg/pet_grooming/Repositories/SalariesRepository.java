/*
 * This is the SalariesRepository Interface
   The SalariesService Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

//Imports
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// Local Import
import com.pg.pet_grooming.Models.Salaries;
import com.pg.pet_grooming.DAO.SalariesByMonth;
import com.pg.pet_grooming.DAO.SalariesByWeek;
import com.pg.pet_grooming.DAO.SalariesByYear;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, Integer> {
    
    // Search Salaries
    // SQL Search Query
    @Query(value = "SELECT * FROM Salaries s WHERE s.salary_id LIKE %:keyword% "
            + "OR s.employee_full_name LIKE %:keyword% OR s.payment_date LIKE %:keyword%", nativeQuery = true)
   List<Salaries> findByKeyword(@Param("keyword") String keyword);
   
    // Get Salaries By Year
    @Query(value="SELECT new com.pg.pet_grooming.DAO.SalariesByYear(EXTRACT(YEAR FROM s.date) AS yr,"
           + "SUM(s.salaryGrandTotal) AS total) FROM Salaries s GROUP BY yr ORDER BY yr")
     List<SalariesByYear> getByYear();
   
    // Get Salaries By Month 
    @Query(value="SELECT new com.pg.pet_grooming.DAO.SalariesByMonth (EXTRACT(YEAR FROM s.date) AS yr, EXTRACT(MONTH FROM s.date) AS mn, "
           + "SUM(s.salaryGrandTotal) AS total) FROM Salaries s GROUP BY yr, mn ORDER BY mn, yr")
    List<SalariesByMonth> getByMonth();
   
    // Get Salaries By Week
      @Query(value="SELECT new com.pg.pet_grooming.DAO.SalariesByWeek (s.date AS startDate,EXTRACT(WEEK FROM s.date) AS wk,"
              + "EXTRACT(MONTH FROM s.date) AS mn, EXTRACT(YEAR FROM s.date) AS yr,"
           + "SUM(s.salaryGrandTotal) AS total) FROM Salaries s GROUP BY wk, mn,yr ORDER BY wk,mn, yr")
    List<SalariesByWeek> getByWeek();
}

