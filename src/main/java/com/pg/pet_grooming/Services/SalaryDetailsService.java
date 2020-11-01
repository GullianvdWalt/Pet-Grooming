/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Models.SalaryDetails;
import com.pg.pet_grooming.Repositories.SalaryDetailsRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryDetailsService {

    @Autowired
    SalaryDetailsRepo salaryDetailsRepo;

    // Get SalaryDetails
    public List<SalaryDetails> getSalaryDetails() {
        return salaryDetailsRepo.findAll();
    }
    // save

    public void saveSalaryDetails(SalaryDetails salaryDetails) {
        salaryDetailsRepo.save(salaryDetails);
    }
    // Find by id

    public Optional<SalaryDetails> getById(Integer id) {
        return salaryDetailsRepo.findById(id);
    }
    // Delete 

    public void deleteSalaryDetails(SalaryDetails salaryDetails) {
        salaryDetailsRepo.delete(salaryDetails);
    }
}

