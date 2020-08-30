/*
 * This is the CashCountService Class
 * This Class implements the CashCountReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Local Imports
import com.pg.pet_grooming.Models.Cash_Count;
import com.pg.pet_grooming.Repositories.CashCountRepository;
@Service
public class CashCountService {
    
// Inject Repository 
@Autowired private CashCountRepository cashCountRepo;

    public List<Cash_Count> getCashCount(){
        return cashCountRepo.findAll();
    }

    public void save(Cash_Count cashCount){
        cashCountRepo.save(cashCount);
    }

    public Optional<Cash_Count> findById(Integer id){
        return cashCountRepo.findById(id);
    }

    public void delete(Integer id) {
        cashCountRepo.deleteById(id);
    }
}
