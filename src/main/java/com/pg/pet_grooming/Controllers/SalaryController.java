/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
 */
package com.pg.pet_grooming.Controllers;

//Imports
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.pg.pet_grooming.DAO.SalariesByMonth;
import com.pg.pet_grooming.DAO.SalariesByWeek;
import com.pg.pet_grooming.DAO.SalariesByYear;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// Local Imports
import com.pg.pet_grooming.Models.BusinessDetails;
import com.pg.pet_grooming.Models.Employees;
import com.pg.pet_grooming.Models.Salaries;
import com.pg.pet_grooming.Models.SalaryDetails;
import com.pg.pet_grooming.Repositories.BusinessDetailsRepository;
import com.pg.pet_grooming.Repositories.EmployeeRepository;
import com.pg.pet_grooming.Repositories.SalariesRepository;
import com.pg.pet_grooming.Repositories.SalaryDetailsRepo;
import com.pg.pet_grooming.Services.EmployeeService;
import com.pg.pet_grooming.Services.SalariesService;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

@Controller
public class SalaryController {

    // Inject Services and Repos
    @Autowired SalariesService salaryService;
    @Autowired EmployeeService employeeService;
    @Autowired SalaryDetailsRepo salaryDetailsRepo;
    @Autowired SalariesRepository salariesRepository;
    @Autowired EmployeeRepository employeeRepository;
    @Autowired BusinessDetailsRepository businessDetailsRepository;

    
    // Salaries with default paging details
    @RequestMapping("/finance/salaries")
    public String getSalaries(Model model) {
        return viewPage(model, "", 1, "date", "desc");
    }
    
    // Method to view all salaies
    @RequestMapping("/finance/salaries/page/{pageNum}")
    public String viewPage(Model model,
            @Param("keyword") String keyword,
            @Valid @PathVariable(name = "pageNum") int pageNum,
            @Valid @Param("sortField") String sortField,
            @Valid @Param("sortDir")String sortDir) {

        // User does not search salaries
        if(keyword == null || keyword == ""){
        
        // List of salaries with paging
        Page<Salaries> page = salaryService.getSalaries(pageNum, sortField, sortDir);
        List<Salaries> listSalaries = page.getContent();    
            
        // Add list to view
        model.addAttribute("listSalaries", listSalaries);
        // Add Paging Details
        model.addAttribute("currentPage", pageNum);		
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        // Add Sorting Details
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        // Sort from asc order to desc
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        }else{ // User made search
            
            List<Salaries> listSalaries = salariesRepository.findByKeyword(keyword);
            // Add list to view
            model.addAttribute("listSalaries", listSalaries);
        }
        
        

        // Set Page Title
        String pageTitle = "Salaries";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "payroll.png";
        model.addAttribute("iconUrl", iconUrl);
        return "Salaries";
    }

        //Get Salaries By Year
    @RequestMapping("/finance/salaries/year")
    public String getSalariesByYear(Model model){
        // Get all salaries By Year
        List<SalariesByYear> salariesByYear = salariesRepository.getByYear();
        // Add List To View
        model.addAttribute("salariesByYear", salariesByYear);
        // Set Page Title
        String pageTitle = "Salaries By Year";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "payroll.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Salaries";
    }
    
    // Get Salaries By Month
    @RequestMapping("/finance/salaries/month")
    public String getSalariesByMonth(Model model){
        // Get all salaries By Month
        List<SalariesByMonth> salariesByMonth = salariesRepository.getByMonth();
        // Add List To View
        model.addAttribute("salariesByMonth", salariesByMonth);
        // Set Page Title
        String pageTitle = "Salaries By Month";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "payroll.png";
        model.addAttribute("iconUrl", iconUrl);
           
        return "Salaries";
    }
    
    // Get Salaries By Week
    @RequestMapping("/finance/salaries/week")
    public String getSalariesByWeek(Model model){
        // Get all salaries By Week
        List<SalariesByWeek> salariesByWeek = salariesRepository.getByWeek();
        // Add List To View
        model.addAttribute("salariesByWeek", salariesByWeek);
        // Set Page Title
        String pageTitle = "Salaries By Week";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "payroll.png";
        model.addAttribute("iconUrl", iconUrl);

        return "Salaries";
    }
    
    
    // Method to retrieve Employees to and return SelectEmployee View
    @RequestMapping("/selectEmployee")
    public String selectEmployee(Model model) {

        List<Employees> employeeList = new ArrayList<>();
        employeeList = employeeService.getEmployees();
        model.addAttribute("employeeList", employeeList);
        // Set Page Title
        String pageTitle = "Select Employee";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "employee.png";
        model.addAttribute("iconUrl", iconUrl);

        return "SelectEmployee";
    }

    @RequestMapping(value = "/select/employee/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String getEmployee(Model model, @PathVariable("id") Integer id, RedirectAttributes redirAttrs) {

        if (id > 0) {
            Employees employee = new Employees();
            employee = employeeService.getEmployeeById(id);
            model.addAttribute("employee", employee);
        } else {
            redirAttrs.addFlashAttribute("error", "Employee not found or not selected!");
            return "/selectEmployee";
        }
        // Set Page Title
        String pageTitle = "Salaries";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "payroll.png";
        model.addAttribute("iconUrl", iconUrl);

        SalaryDetails salaryDetails = new SalaryDetails();
        salaryDetails = salaryDetailsRepo.getOne(1);

        // Add Salary Details 
        model.addAttribute("wage", salaryDetails.getWage());
        model.addAttribute("overtime", salaryDetails.getOvertime());
        model.addAttribute("bonusL", salaryDetails.getBonus_large());
        model.addAttribute("bonusM", salaryDetails.getBonus_medium());
        model.addAttribute("bonusS", salaryDetails.getBonus_small());
        model.addAttribute("transportAmount", salaryDetails.getTransport_amount());

        return "AddSalary";
    }

    @RequestMapping("/finance/addSalary")
    public String getAddSalary(Model model) {
        // Set Page Title
        String pageTitle = "Add Salary";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "payroll.png";
        model.addAttribute("iconUrl", iconUrl);
        return "AddSalary";
    }

    // Save Salary
    @RequestMapping(value = "/salary/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveSalary(Model model, Salaries salary, RedirectAttributes redirAttrs,
            @RequestParam("pay_period_start") String pay_sDate, @RequestParam("pay_period_end") String pay_eDate,
            @RequestParam("payment_date") String pay_date) throws ParseException, IOException {

        // Convert Strings to date
        // DateFormatter
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ROOT);

        Date payDate = (Date) dateTimeFormat.parse(pay_date);
        System.out.println(payDate);
        salary.setDate(payDate);

        Date startDate = (Date) dateTimeFormat.parse(pay_sDate);
        System.out.println(startDate);
        salary.setPay_period_start(startDate);

        Date endDate = (Date) dateTimeFormat.parse(pay_eDate);
        System.out.println(endDate);
        salary.setPay_period_end(endDate);

        salaryService.saveSalary(salary);
        redirAttrs.addFlashAttribute("success", "Salary Saved!");
        return "redirect:/salaries/payslip/" + salary.getSalaryId();
    }

    // Method to hanlde exporting of PDF Payslips
    @RequestMapping("/salaries/payslip/{id}")
    public String exportToPdf(@PathVariable("id") Integer id, Model model) {
        Salaries salaries = salariesRepository.getOne(id);
        Employees employee = employeeRepository.getOne(salaries.getEmployee_id());
        BusinessDetails businessDetails = businessDetailsRepository.getOne(1);
        SalaryDetails salaryDetails = salaryDetailsRepo.getOne(1);

        // Add Object to view
        model.addAttribute("salaries", salaries);
        model.addAttribute("employee", employee);
        model.addAttribute("businessDetails", businessDetails);
        model.addAttribute("salaryDetails", salaryDetails);

        // Set Page Title
        String pageTitle = "Payslip";
        model.addAttribute("pageTitle", pageTitle);
        // Set Page Title Icon
        String iconUrl = "payslip.png";
        model.addAttribute("iconUrl", iconUrl);
        return "Payslip";
    }

    // Method to Create PDF with Velocity(HTML to PDF)
    @GetMapping("/genPdf/{fileName}/{id}")
    HttpEntity<byte[]> createPdf(@PathVariable("fileName") String fileName,
            @PathVariable("id") Integer id) throws IOException {

        Salaries salaries = salariesRepository.getOne(id);
        Employees employee = employeeRepository.getOne(salaries.getEmployee_id());
        BusinessDetails businessDetails = businessDetailsRepository.getOne(1);
        SalaryDetails salaryDetails = salaryDetailsRepo.getOne(1);

        // Get and Initialize Velocity Engine
        VelocityEngine ve = new VelocityEngine();

        // Get template
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());
        ve.init();
        // Set Template to HTML file
        Template template = ve.getTemplate("templates/payslipExport.vm");
        // Create Context and add data
        VelocityContext context = new VelocityContext();
        // Add Data
        context.put("genDateTime", LocalDateTime.now().toString());
        context.put("date", salaries.getDate().toString());
        context.put("businessName", businessDetails.getBusiness_name());
        context.put("businessAddress", businessDetails.getBusiness_address().toString());
        context.put("employeeName", salaries.getEmployeeFullName());
        context.put("saID", employee.getEmp_sa_id().toString());
        context.put("occupation", employee.getOccupation());
        context.put("startDate", salaries.getPay_period_start().toString());
        context.put("endDate", salaries.getPay_period_end().toString());
        context.put("wage", salaryDetails.getWage().toString());
        context.put("paymethod", salaries.getPayment_method().toString());
        context.put("subTotal", salaries.getSalary_subtotal().toString());
        context.put("overtimeHours", salaries.getOvertime_hours().toString());
        context.put("subTotal", salaries.getSalary_subtotal().toString());
        context.put("overtime", salaryDetails.getOvertime().toString());
        context.put("overtimeTotal", salaries.getOvertimeTotal().toString());

        Double totalInclOvertime = salaries.getSalary_subtotal() + salaries.getOvertimeTotal();

        context.put("totalInclOvertime", totalInclOvertime.toString());
        context.put("deduction_description", salaries.getDeduction_description());
        
        double deductionTotal = salaries.getDeduction_total();
        if(deductionTotal <= 0){
            deductionTotal = 0;           
        }
        
        context.put("deduction_total", salaries.getDeduction_total().toString());
        context.put("total_aftr_deduct", salaries.getSalary_total_aftr_deduct().toString());
        context.put("transport_amount", salaryDetails.getTransport_amount().toString());
        context.put("transport_total", salaries.getTransport_total().toString());
        context.put("numSDog", salaries.getNumSDog().toString());
        context.put("numMDog", salaries.getNumMDog().toString());
        context.put("numLDog", salaries.getNumLDog().toString());
        context.put("bonus_small", salaryDetails.getBonus_small().toString());
        context.put("bonus_medium", salaryDetails.getBonus_small().toString());
        context.put("bonus_large", salaryDetails.getBonus_small().toString());
        context.put("amountSDog", salaries.getAmountSDog().toString());
        context.put("amountMDog", salaries.getAmountMDog().toString());
        context.put("amountLDog", salaries.getAmountLDog().toString());
        context.put("bonus_total", salaries.getBonusTotal().toString());
        context.put("salary_total_w_bonus", salaries.getSalary_total_w_bonus().toString());

        // Render Template Into Stringwriter
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        System.out.println(writer.toString());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = generatePdf(writer.toString());
        fileName = "Payslip_" + salaries.getEmployeeFullName() + "_" + salaries.getDate();
        // Create Header
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; fileName=" + fileName.replace(" ", "_"));
        header.setContentLength(baos.toByteArray().length);

        return new HttpEntity<byte[]>(baos.toByteArray(), header);

    }

    private ByteArrayOutputStream generatePdf(String html) {
        String pdfFilePath = "";
        PdfWriter pdfWriter = null;

        // Create New Document
        Document document = new Document();

        try {
            document = new Document();
            // Document Header Attributes
            document.addAuthor("Gullian Van Der Walt");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("Gullian Van Der Walt");
            document.addTitle("PAYSLIP");
            document.setPageSize(PageSize.A4);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            // Open Document
            document.open();

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);
            xmlWorkerHelper.getDefaultCSS();
            // Convert HTML
            xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(html));

            // Close The Document
            document.close();
            System.out.println("PDF generated Successfully");

            return baos;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

}

