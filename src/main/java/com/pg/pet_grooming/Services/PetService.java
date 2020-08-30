/*
 * This is the Pet Service Class
 * This Class implements the PetReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Local Imports
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Models.Pet;
import java.util.List;

@Service
public class PetService {
    
    // Inject PetRepository
    @Autowired private PetRepository petRepository;
   
    // Return Pets
    public List<Pet> getEmployees(){
        return petRepository.findAll();
    }
    
    //Save New Pet From New Pet Form To DB
    public void save(Pet pet){
        petRepository.save(pet);
    }
    
    // Delete Pet
    public void deletePet(Integer id){
        petRepository.deleteById(id);
    }
    
    
   // Get Pet By Keyword from SQL Query Defined in the PetRepository
   public List<Pet> findPetByKeyword(String keyword){
       return petRepository.findByKeyword(keyword);
   }
}
