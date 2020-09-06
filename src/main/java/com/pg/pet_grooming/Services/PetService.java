/*
 * This is the Pet Service Class
 * This Class implements the PetReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Local Imports
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;

@Service
public class PetService {
    
    // Inject PetRepository
    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;
   
    // Return Pets
    public List<Pet> getPets(){
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
    
    // Get Pet By Owner ID
    public Optional<PetOwner> findPetOwnerById(Integer id){
        return petOwnerRepository.findById(id);
    }
       // Get Pet By ID
    public Optional<Pet> findPetById(Integer id){
        return petRepository.findById(id);
    }
    
   // Get Pet By Keyword from SQL Query Defined in the PetRepository
   public List<Pet> findPetByKeyword(String keyword){
       return petRepository.findByKeyword(keyword);
   }
}
