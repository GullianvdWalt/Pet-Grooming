/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Repositories;


import com.pg.pet_grooming.DAO.IncomeByMonth;
import com.pg.pet_grooming.DAO.IncomeByWeek;
import com.pg.pet_grooming.DAO.IncomeByYear;
import com.pg.pet_grooming.Models.Income;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer>{
    
      // Get Income By Year
    @Query(value="SELECT new com.pg.pet_grooming.DAO.IncomeByYear(EXTRACT(YEAR FROM i.invoiceDate) AS yr,"
           + "SUM(i.amount) AS total) FROM Income i GROUP BY yr ORDER BY yr")
     List<IncomeByYear> getByYear();
   
    // Get Income By Month 
    @Query(value="SELECT new com.pg.pet_grooming.DAO.IncomeByMonth (EXTRACT(YEAR FROM i.invoiceDate) AS yr, EXTRACT(MONTH FROM i.invoiceDate) AS mn, "
           + "SUM(i.amount) AS total) FROM Income i GROUP BY yr, mn ORDER BY yr DESC")
    List<IncomeByMonth> getByMonth();
   
    // Get Income By Week
      @Query(value="SELECT new com.pg.pet_grooming.DAO.IncomeByWeek (i.invoiceDate AS startDate,EXTRACT(WEEK FROM i.invoiceDate) AS wk,"
              + "EXTRACT(MONTH FROM i.invoiceDate) AS mn, EXTRACT(YEAR FROM i.invoiceDate) AS yr,"
           + "SUM(i.amount) AS total) FROM Income i GROUP BY wk, mn,yr ORDER BY yr DESC")
    List<IncomeByWeek> getByWeek();  
    

}
