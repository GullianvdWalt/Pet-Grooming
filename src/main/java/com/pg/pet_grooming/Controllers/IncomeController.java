/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Controllers;

// Imports
import com.pg.pet_grooming.DAO.IncomeByMonth;
import com.pg.pet_grooming.DAO.IncomeByWeek;
import com.pg.pet_grooming.DAO.IncomeByYear;
import com.pg.pet_grooming.Models.Income;
import com.pg.pet_grooming.Repositories.IncomeRepository;
import com.pg.pet_grooming.Services.IncomeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IncomeController {

    @Autowired private IncomeService incomeService;
    @Autowired private IncomeRepository incomeRepository;
    
    // Get View Income Main Page, default paging variables
    @RequestMapping("/finance/income")
    public String showIncome(Model model) {
        return viewPage(model, 1, "id", "desc");
    }
    
    // View Income By Page
    @RequestMapping("/finance/income/page/{pageNum}")
    public String viewPage(Model model,
            @Valid @PathVariable(name = "pageNum") int pageNum,
            @Valid @Param("sortField") String sortField,
            @Valid @Param("sortDir")String sortDir) {
        
        Page<Income> page = incomeService.getIncome(pageNum, sortField, sortDir);
        List<Income> incomeList = page.getContent();
        
        // Add List of to view
       model.addAttribute("incomeList", incomeList);
       
        // Add Paging Details
       model.addAttribute("currentPage", pageNum);		
       model.addAttribute("totalPages", page.getTotalPages());
       model.addAttribute("totalItems", page.getTotalElements());
       // Add Sorting Details
       model.addAttribute("sortField", sortField);
       model.addAttribute("sortDir", sortDir);
       // Sort from asc order to desc
       model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        // Set Page Title
        String pageTitle = "Income";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "income.png";
        model.addAttribute("iconUrl", iconUrl);
        return "Income";
    }
    
            //Get Income By Year
    @RequestMapping("/finance/income/year")
    public String getIncomeByYear(Model model){
        // Get income By Year
        List<IncomeByYear> incomeByYear = incomeRepository.getByYear();
        // Add List To View
        model.addAttribute("incomeByYear", incomeByYear);
        // Set Page Title
        String pageTitle = "Income By Year";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "income.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Income";
    }
    
    // Get Income By Month
    @RequestMapping("/finance/income/month")
    public String getIncomeByMonth(Model model){
        // Get income By Month
        List<IncomeByMonth> incomeByMonth = incomeRepository.getByMonth();
        // Add List To View
        model.addAttribute("incomeByMonth", incomeByMonth);
        // Set Page Title
        String pageTitle = "Income By Month";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "income.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Income";
    }
    
    // Get Income By Week
    @RequestMapping("/finance/income/week")
    public String getIncomeByWeek(Model model){
        // Get all salaries By Week
        List<IncomeByWeek> incomeByWeek = incomeRepository.getByWeek();
        // Add List To View
        model.addAttribute("incomeByWeek", incomeByWeek);
        // Set Page Title
        String pageTitle = "Income By Week";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "income.png";
        model.addAttribute("iconUrl", iconUrl);

        return "Income";
    }
    
    

}

