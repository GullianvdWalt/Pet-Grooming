/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Controllers;
// Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// Local Imports
import com.pg.pet_grooming.DAO.ExpensesByMonth;
import com.pg.pet_grooming.DAO.ExpensesByYear;
import com.pg.pet_grooming.DAO.ExpensesByWeek;
import com.pg.pet_grooming.DAO.IncomeByMonth;
import com.pg.pet_grooming.DAO.IncomeByYear;
import com.pg.pet_grooming.DAO.IncomeByWeek;
import com.pg.pet_grooming.DAO.ProfitByMonth;
import com.pg.pet_grooming.DAO.ProfitByYear;
import com.pg.pet_grooming.DAO.ProfitByWeek;
import com.pg.pet_grooming.DAO.SalariesByMonth;
import com.pg.pet_grooming.DAO.SalariesByYear;
import com.pg.pet_grooming.DAO.SalariesByWeek;
import com.pg.pet_grooming.Models.Profit;
import com.pg.pet_grooming.Repositories.ExpensesRepository;
import com.pg.pet_grooming.Repositories.IncomeRepository;
import com.pg.pet_grooming.Repositories.ProfitRepository;
import com.pg.pet_grooming.Repositories.SalariesRepository;
import com.pg.pet_grooming.Services.ProfitService;

@Controller
public class ProfitController {
   
    @Autowired private ProfitService profitService;
    @Autowired private ProfitRepository profitRepository;
    @Autowired private IncomeRepository incomeRepo;
    @Autowired private ExpensesRepository expensesRepo;
    @Autowired private SalariesRepository salaryRepo;
    
    @RequestMapping("/finance/profit")
    public String getProfit(Model model){
        return viewPage(model, 1, "id", "asc");
    }
    // (View By Day)    
    @RequestMapping("/finance/profit/page/{pageNum}")
    public String viewPage(Model model,
            @Valid @PathVariable(name = "pageNum") int pageNum,
            @Valid @Param("sortField") String sortField,
            @Valid @Param("sortDir")String sortDir){

        Page<Profit> page = profitService.getProfit(pageNum, sortField, sortDir);
        List<Profit> profitList = page.getContent();

        // Add List of to view
       model.addAttribute("profitList", profitList);
       
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
        String pageTitle = "Profit";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspenseReport.png";
        model.addAttribute("iconUrl", iconUrl);
        
                
        return "Profit";
    }
     
     //Get Income By Year
    @RequestMapping("/finance/profit/year")
    public String getProfitByYear(Model model){
        // Object Lists
        List<IncomeByYear> incomeByYearList = incomeRepo.getByYear();
        List<ExpensesByYear> expensesByYearList = expensesRepo.getByYear();
        List<SalariesByYear> salariesByYearList = salaryRepo.getByYear();
        List<ProfitByYear> profitByYearList = new ArrayList<>();
        List<ProfitByYear> profitByYearListSal = new ArrayList<>();
        
        //ProfitByYear[] profitByYear;

        for (int j = 0; j < incomeByYearList.size(); j++) {
            // Create new object with each loop
            ProfitByYear profitByYear = new ProfitByYear();
                        
            // Excluding Salary Deduction
            
            // Get and set year
            profitByYear.setYr(incomeByYearList.get(j).getYr());  
            
            // Get the profit total excl Salaries
            profitByYear.setTotal((incomeByYearList.get(j).getTotal()) - expensesByYearList.get(j).getTotal());

            // Add to list of profitByYearObjects
            profitByYearList.add(profitByYear); 
        }
     
        for (int i = 0; i < incomeByYearList.size(); i++) {
            // Including Salary Deduction
            ProfitByYear profitByYearSal = new ProfitByYear();
            
            // Get and set year
            profitByYearSal.setYr(incomeByYearList.get(i).getYr());  
            
            // Get and set profit total incl Salaries
            profitByYearSal.setTotal((incomeByYearList.get(i).getTotal()) - 
                    (expensesByYearList.get(i).getTotal() + salariesByYearList.get(i).getTotal()));
            // Deduct Salary
            

            // Add to list of profitByYearObjects
            profitByYearListSal.add(profitByYearSal); 
   
        }
        
        model.addAttribute("profitByYearList", profitByYearList);
        model.addAttribute("profitByYearListSal", profitByYearListSal);
        // Set Page Title
        String pageTitle = "Profit By Year";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspenseReport.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Profit";
    }
    
    // Get Income By Month
    @RequestMapping("/finance/profit/month")
    public String getProfiteByMonth(Model model){

        List<IncomeByMonth> incomeByMonthList = incomeRepo.getByMonth();
        List<ExpensesByMonth> expensesByMonthList = expensesRepo.getByMonth();
        List<SalariesByMonth> salariesByMonthList = salaryRepo.getByMonth();
        List<ProfitByMonth> profitByMonthList = new ArrayList<>();
        List<ProfitByMonth> profitByMonthListSal = new ArrayList<>();
        
        for (int j = 0; j < incomeByMonthList.size(); j++) {
            // Create new object with each loop
            ProfitByMonth profitByMonth = new ProfitByMonth();
                        
            // Excluding Salary Deduction
            // Get and set Month
            profitByMonth.setMn(incomeByMonthList.get(j).getMn());
            // Get and set year
            profitByMonth.setYr(incomeByMonthList.get(j).getYr()); 
            
            // Get the profit total excl Salaries
            profitByMonth.setTotal((incomeByMonthList.get(j).getTotal()) - expensesByMonthList.get(j).getTotal());

            // Add to list of profitByYearObjects
            profitByMonthList.add(profitByMonth); 
        }
     
        for (int i = 0; i < incomeByMonthList.size(); i++) {
            // Including Salary Deduction
            ProfitByMonth profitByMonthSal = new ProfitByMonth();

            // Deduct Salary
             // Get and set Month
            profitByMonthSal.setMn(incomeByMonthList.get(i).getMn()); 
            // Get and set year
            profitByMonthSal.setYr(incomeByMonthList.get(i).getYr()); 
            // Get the profit total excl Salaries
            profitByMonthSal.setTotal((incomeByMonthList.get(i).getTotal()) - 
                    (expensesByMonthList.get(i).getTotal() + salariesByMonthList.get(i).getTotal()));

            // Add to list of profitByYearObjects
            profitByMonthListSal.add(profitByMonthSal); 
   
        }

        model.addAttribute("profitByMonthList", profitByMonthList);
        model.addAttribute("profitByMonthListSal", profitByMonthListSal);
        
        // Set Page Title
        String pageTitle = "Profit By Month";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspenseReport.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Profit";
    }
    
    // Get Profit By Week
    @RequestMapping("/finance/profit/week")
    public String getprofitByWeek(Model model){
        
        List<IncomeByWeek> incomeByWeekList = incomeRepo.getByWeek();
        List<ExpensesByWeek> expensesByWeekList = expensesRepo.getByWeek();
        List<SalariesByWeek> salariesByWeekList = salaryRepo.getByWeek();
        List<ProfitByWeek> profitByWeekList = new ArrayList<>();
        List<ProfitByWeek> profitByWeekListSal = new ArrayList<>();
        
        for (int j = 0; j < incomeByWeekList.size(); j++) {
            // Create new object with each loop
            ProfitByWeek profitByWeek = new ProfitByWeek();
                        
            // Excluding Salary Deduction
            // Get and Set Start Date
            profitByWeek.setStartDate(incomeByWeekList.get(j).getStartDate());
            // Get and Set Week
            profitByWeek.setWk(incomeByWeekList.get(j).getWk());
            // Get and set Month
            profitByWeek.setMn(incomeByWeekList.get(j).getMn());
            // Get and set year
            profitByWeek.setYr(incomeByWeekList.get(j).getYr()); 
            System.out.println(incomeByWeekList.get(j).getWk());
            // Get the profit total excl Salaries
            profitByWeek.setTotal((incomeByWeekList.get(j).getTotal()) - expensesByWeekList.get(j).getTotal());

            // Add to list of profitByYearObjects
            profitByWeekList.add(profitByWeek); 
        }
     
        for (int i = 0; i < incomeByWeekList.size(); i++) {
            // Including Salary Deduction
            ProfitByWeek profitByWeekSal = new ProfitByWeek();

            // Deduct Salary
            // Get and Set Start Date
            profitByWeekSal.setStartDate(incomeByWeekList.get(i).getStartDate());
            // Get and Set Week
            profitByWeekSal.setWk(incomeByWeekList.get(i).getWk());
             // Get and set Month
            profitByWeekSal.setMn(incomeByWeekList.get(i).getMn()); 
            // Get and set year
            profitByWeekSal.setYr(incomeByWeekList.get(i).getYr()); 
            // Get the profit total excl Salaries
            profitByWeekSal.setTotal((incomeByWeekList.get(i).getTotal()) - 
                    (expensesByWeekList.get(i).getTotal() + salariesByWeekList.get(i).getTotal()));

            // Add to list of profitByYearObjects
            profitByWeekListSal.add(profitByWeekSal); 
   
        }

        model.addAttribute("profitByWeekList", profitByWeekList);
        model.addAttribute("profitByWeekListSal", profitByWeekListSal);
        // Set Page Title
        String pageTitle = "Profit By Week";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "exspenseReport.png";
        model.addAttribute("iconUrl", iconUrl);

        return "Profit";
    }
}

