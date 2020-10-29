/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
 */
// Class Used to genereate PDF Document for Salaries
package com.pg.pet_grooming.Utilities;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.pg.pet_grooming.Models.Employees;
import com.pg.pet_grooming.Models.Salaries;
import com.pg.pet_grooming.Repositories.SalariesRepository;
import java.awt.Color;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class PDFExporter {

    private Salaries salaries;
    private Employees employee;

    @Autowired
    SalariesRepository salaryRepository;

    public PDFExporter(Salaries salaries, Employees employee) {
        this.salaries = salaries;
        this.employee = employee;
    }

    public void getSalary(Integer id) {
        Salaries salary = salaryRepository.getOne(id);
        System.out.println(salary.getDate());
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.darkGray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Date: ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Employee Id: ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Employee Name: ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Salary: ", font));
        table.addCell(cell);
    }

    // Set Data to PDF
    private void writeTableData(PdfPTable table) {
        table.addCell(String.valueOf(salaries.getDate()));
        table.addCell(String.valueOf(employee.getId()));
        table.addCell(employee.getEmployee_full_name());
        table.addCell(String.valueOf(salaries.getSalaryGrandTotal()));
    }

    public void export(HttpServletResponse response) throws IOException {
        // Set PDF Page Size
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.LIGHT_GRAY);

        Paragraph paragraph = new Paragraph("Payslip", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}

