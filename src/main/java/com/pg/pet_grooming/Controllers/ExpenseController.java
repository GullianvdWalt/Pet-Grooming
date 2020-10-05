/*
 * Created By Gullian Van Der Walt
 * 04/09/2020
 * 
 */
package com.pg.pet_grooming.Controllers;

// Imports
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// Local Imports
import com.pg.pet_grooming.Models.Expenses;
import com.pg.pet_grooming.Services.ExpensesService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExpenseController {
    
    @Autowired ExpensesService expenseService;
    
     // Main Expense View
    @RequestMapping("/finance/expenses")
    public String getExpenses(Model model){
       
      Expenses expense = new Expenses();
      
      List<Expenses> expensesList = expenseService.getExpenses();
      
      model.addAttribute("expensesList", expensesList);
        
      // Set Page Title
      String pageTitle = "Expenses";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "exspense.png";
      model.addAttribute("iconUrl", iconUrl);
      model.addAttribute("expense", expense);
        return "Expenses";
    }   
    
         // Main Expense View
    @RequestMapping("/finance/expense/add")
    public String addExpenses(Model model){
       
      Expenses expense = new Expenses();
        
      // Set Page Title
      String pageTitle = "New Expense";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "exspense.png";
      model.addAttribute("iconUrl", iconUrl);
      model.addAttribute("expense", expense);
        return "AddExpense";
    } 
    
    // Add Expense
    @PostMapping("/finance/expense/new")
    public String newdExpense(Expenses expense,
        RedirectAttributes redirAttrs,BindingResult result,Model model,
        @RequestParam("expense_date") String expense_date) throws ParseException{
            
        
                            // Convert String to Date
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        Date date = (Date)dateTimeFormat.parse(expense_date);
         expense.setExpense_date(date);
         
        if(result.hasErrors()){
            return "Expenses";
        }else{
            

            expenseService.saveExpense(expense);
            redirAttrs.addFlashAttribute("success", "Expense Saved");
        }
        
        return"redirect:/finance/expenses";
    }
    
    
    @RequestMapping("/finance/expenseReports")
    public String getExpenseReports(Model model){
      // Set Page Title
      String pageTitle = "Expense Reports";
      model.addAttribute("pageTitle", pageTitle);
      // Set Page Title Icon
      String iconUrl = "exspense.png";
      model.addAttribute("iconUrl", iconUrl);
       return "ExpenseReports";
    } 
    
}
