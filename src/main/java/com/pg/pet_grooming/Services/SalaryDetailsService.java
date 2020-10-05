/*
    Created By Gullian Van Der Walt - 2020/10/04, 15:26
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
    
@Autowired SalaryDetailsRepo salaryDetailsRepo;
    
   // Get SalaryDetails
   public List<SalaryDetails> getSalaryDetails(){
       return salaryDetailsRepo.findAll();
   }
   // save
   public void saveSalaryDetails(SalaryDetails salaryDetails){
       salaryDetailsRepo.save(salaryDetails);
   }
   // Find by id
   public Optional<SalaryDetails> getById(Integer id){
       return salaryDetailsRepo.findById(id);
   }
   // Delete 
   public void deleteSalaryDetails(SalaryDetails salaryDetails){
       salaryDetailsRepo.delete(salaryDetails);
   }
}
