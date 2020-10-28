/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
 */
package com.pg.pet_grooming.Controllers;

// Imports
import com.pg.pet_grooming.DAO.ExpensesByMonth;
import com.pg.pet_grooming.DAO.ExpensesByWeek;
import com.pg.pet_grooming.DAO.ExpensesByYear;
import com.pg.pet_grooming.Models.Expenses;
import com.pg.pet_grooming.Repositories.ExpensesRepository;
import com.pg.pet_grooming.Services.ExpensesService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExpenseController {

    @Autowired ExpensesService expenseService;
    @Autowired ExpensesRepository expensesRepository;
    
    // Main Expense View return paging defaults
    @RequestMapping("/finance/expenses")
    public String getExpenses(Model model) {
        return viewPage(model, "", 1, "expenseDate", "desc");
    }
    
    // Main Expense View with paging
    @RequestMapping("/finance/expenses/page/{pageNum}")
    public String viewPage(Model model,
            @Param("keyword") String keyword,
            @Valid @PathVariable(name = "pageNum") int pageNum,
            @Valid @Param("sortField") String sortField,
            @Valid @Param("sortDir")String sortDir) {

        Expenses expense = new Expenses();


        // Set Page Title
        String pageTitle = "Expenses";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspense.png";
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("expense", expense);
        
        // Initialy the user doesn't make search 
        if(keyword == null || keyword == ""){
         // Get pageable list
         Page<Expenses> page = expenseService.getExpenses(pageNum, sortField, sortDir);
         // Add to list
         List<Expenses> expensesList = page.getContent();
         // Add list to view
         model.addAttribute("expensesList", expensesList);
         
        // Add Paging Details
        model.addAttribute("currentPage", pageNum);		
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        // Add Sorting Details
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        // Sort from asc order to desc
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        }else{ //User made a search
            List<Expenses> expensesList = expensesRepository.findByKeyword(keyword);
            // Add searched list to view
            model.addAttribute("expensesList", expensesList);
        }
        
        return "Expenses";
    }

    // Main Expense View
    @RequestMapping("/finance/expense/add")
    public String addExpenses(Model model) {

        //Expenses expense = new Expenses();

        // Set Page Title
        String pageTitle = "New Expense";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspense.png";
        model.addAttribute("iconUrl", iconUrl);
        //model.addAttribute("expense", expense);
        return "AddExpense";
    }

    // Add Expense
    @PostMapping("/finance/expense/new")
    public String newdExpense(Expenses expense,
            RedirectAttributes redirAttrs, BindingResult result, Model model,
            @RequestParam("expenseDate") String expense_date) throws ParseException {

        // Convert String to Date
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        Date date = (Date) dateTimeFormat.parse(expense_date);
        expense.setExpenseDate(date);

        if (result.hasErrors()) {
            return "Expenses";
        } else {

            expenseService.saveExpense(expense);
            redirAttrs.addFlashAttribute("success", "Expense Saved");
        }

        return "redirect:/finance/expenses";
    }

    //Get Expenses By Year
    @RequestMapping("/finance/expenses/year")
    public String getExpensesByYear(Model model){
        // Get all expenses By Year
        List<ExpensesByYear> expensesByYear = expensesRepository.getByYear();
        // Add List To View
        model.addAttribute("expensesByYear", expensesByYear);
        // Set Page Title
        String pageTitle = "Expenses By Year";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspense.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Expenses";
    }
    
    // Get Expenses By Month
    @RequestMapping("/finance/expenses/month")
    public String getExpensesByMonth(Model model){
        // Get all expenses By Month
        List<ExpensesByMonth> expensesByMonth = expensesRepository.getByMonth();
        // Add List To View
        model.addAttribute("expensesByMonth", expensesByMonth);
        // Set Page Title
        String pageTitle = "Expenses By Month";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspense.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Expenses";
    }
    
    // Get Expenses By Week
    @RequestMapping("/finance/expenses/week")
    public String getExpensesByWeek(Model model){
        // Get all expenses By Week
        List<ExpensesByWeek> expensesByWeek = expensesRepository.getByWeek();
        // Add List To View
        model.addAttribute("expensesByWeek", expensesByWeek);
        // Set Page Title
        String pageTitle = "Expenses By Week";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspense.png";
        model.addAttribute("iconUrl", iconUrl);

        return "Expenses";
    }    

}

