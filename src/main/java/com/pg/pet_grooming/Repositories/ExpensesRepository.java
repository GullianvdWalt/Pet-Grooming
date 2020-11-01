/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Repositories;

// Imports
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// Local Importss
import com.pg.pet_grooming.DAO.ExpensesByMonth;
import com.pg.pet_grooming.Models.Expenses;
import com.pg.pet_grooming.DAO.ExpensesByWeek;
import com.pg.pet_grooming.DAO.ExpensesByYear;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {

    // Search Expenses
    // SQL Search Query
    @Query(value = "SELECT * FROM expenses e WHERE e.expense_id LIKE %:keyword% "
            + "OR e.expense_amount LIKE %:keyword% OR e.expense_type LIKE %:keyword% "
            + "OR e.expense_description LIKE %:keyword% "
            + "OR e.expense_date LIKE %:keyword%", nativeQuery = true)
   List<Expenses> findByKeyword(@Param("keyword") String keyword);

 
    // Get Expenses By Year
    @Query(value="SELECT new com.pg.pet_grooming.DAO.ExpensesByYear(EXTRACT(YEAR FROM e.expenseDate) AS yr,"
           + "SUM(e.expense_amount) AS total) FROM Expenses e GROUP BY yr ORDER BY yr")
     List<ExpensesByYear> getByYear();
   
    // Get Expenses By Month 
    @Query(value="SELECT new com.pg.pet_grooming.DAO.ExpensesByMonth (EXTRACT(YEAR FROM e.expenseDate) AS yr, EXTRACT(MONTH FROM e.expenseDate) AS mn, "
           + "SUM(e.expense_amount) AS total) FROM Expenses e GROUP BY yr, mn ORDER BY yr DESC")
    List<ExpensesByMonth> getByMonth();
   
    // Get Expenses By Week
      @Query(value="SELECT new com.pg.pet_grooming.DAO.ExpensesByWeek (e.expenseDate AS startDate,EXTRACT(WEEK FROM e.expenseDate) AS wk,"
              + "EXTRACT(MONTH FROM e.expenseDate) AS mn, EXTRACT(YEAR FROM e.expenseDate) AS yr,"
           + "SUM(e.expense_amount) AS total) FROM Expenses e GROUP BY wk, mn,yr ORDER BY yr DESC")
    List<ExpensesByWeek> getByWeek();
   
}

