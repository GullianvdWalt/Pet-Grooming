/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Repositories;

import com.pg.pet_grooming.DAO.ExpensesByYear;
import com.pg.pet_grooming.DAO.ProfitByDay;
import com.pg.pet_grooming.Models.Profit;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepository  extends JpaRepository<Profit, Integer>{
    
    //"SELECT new com.pg.pet_grooming.DAO.ExpensesByYear(EXTRACT(YEAR FROM e.expenseDate) AS yr,"
           //+ "SUM(e.expense_amount) AS total) FROM Expenses e GROUP BY yr ORDER BY yr"
    
    // Get Profit By Day
    @Query(value="SELECT new com.pg.pet_grooming.DAO.ProfitByDay (i.invoiceDate AS dt,"
           + "(i.amount - (e.expense_amount + s.salaryGrandTotal)) AS total) FROM Income i, Expenses e, Salaries s "
           + "WHERE i.invoiceDate LIKE %:date% AND e.expenseDate LIKE %:date% "
           + "AND s.date LIKE %:date% ORDER BY dt")
    List<ProfitByDay> getByDay(Date date);
}