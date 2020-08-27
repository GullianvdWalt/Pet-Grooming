/*
 * This is the Pet Service Class
 * This Class implements the PetReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Local Imports
import com.pg.pet_grooming.Repositories.PetRepository;
import com.pg.pet_grooming.Models.Pet;

@Service
public class PetService {
    // Inject PetRepository
    @Autowired
    private PetRepository petRepository;
   
    //Save New Pet From New Pet Form To DB
    public void save(Pet pet){
        petRepository.save(pet);
    }
}
