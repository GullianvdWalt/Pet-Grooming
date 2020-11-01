/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Pet;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import com.pg.pet_grooming.Repositories.PetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    // Inject PetRepository
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    // Return Pets
    public List<Pet> getPets() {
        
        return petRepository.findAll();
    }

    //Save New Pet From New Pet Form To DB
    public void save(Pet pet) {
        petRepository.save(pet);
    }

    // Delete Pet
    public void deletePet(Integer id) {
        petRepository.deleteById(id);
    }

    // Get Pet By Owner ID
    public Optional<PetOwner> findPetOwnerById(Integer id) {
        return petOwnerRepository.findById(id);
    }

    // Get Pets by PetOwnerID
    public List<Pet> findPetByPetOwnerId(int petOwnerId) {
        return petRepository.getPetByPetOwnerId(petOwnerId);
    }

    // Get Pet By ID
    public Optional<Pet> findPetById(int id) {
        return petRepository.findById(id);
    }

    // Get Pet By Keyword from SQL Query Defined in the PetRepository
    public Pet findPetByKeyword(String keyword) {
        return petRepository.findByKeyword(keyword);
    }
}

