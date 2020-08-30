/*
 * This is the Pet Owner Service Class
 * This Class implements the Pet Owner Reposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Local Imports
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Models.PetOwner;

@Service
public class PetOwnerService {
    
    //Inject PetRepository
    @Autowired
    private PetOwnerRepository petOwnerRepository;
    
    //Method To Return A List of PetOwners FROM MySQL Database, PetOwner Table
    public List<PetOwner> getPetOwners(){
        return petOwnerRepository.findAll();
    }

    //Save New Pet Owner From NewCustomer Form
    public void save(PetOwner petOwner){
        petOwnerRepository.save(petOwner);
    }

   //Get PetOwner by Id
    public Optional<PetOwner> findById(Integer id){
        return petOwnerRepository.findById(id);
    }

    // Delete PetOwner By ID
    public void delete(Integer id) {
        petOwnerRepository.deleteById(id);
    }
    
   // Get PetOwner By Keyword from SQL Query Defined in the PetOwnerRepository
   public List<PetOwner> findPetOwnerByKeyword(String keyword){
       return petOwnerRepository.findByKeyword(keyword);
   }

}
