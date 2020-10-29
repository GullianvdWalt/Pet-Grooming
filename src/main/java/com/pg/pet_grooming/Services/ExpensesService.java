/*
    Created By Gullian Van Der Walt
    Last Updated - 2020/10/01, 13:30
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Expenses;
import com.pg.pet_grooming.Repositories.ExpensesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {

// Inject Repository 
    @Autowired
    private ExpensesRepository expenseRepository;

    // Return Expenses
    public Page<Expenses> getExpenses(int pageNum, String sortField, String sortDir) {
        // Paging - 6 rows per page
        Pageable pageable = PageRequest.of(pageNum - 1, 10, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
        
        return expenseRepository.findAll(pageable);
    }

    // Save New Expense
    public void saveExpense(Expenses expense) {
        expenseRepository.save(expense);
    }

    // Get Expense
    public Optional<Expenses> findExpensById(int id) {
        return expenseRepository.findById(id);
    }

    // Delete Expense
    public void deleteExpense(Integer id) {
        expenseRepository.deleteById(id);
    }
}

