/*
 * This is the Pet Owner Service Class
 * This Class implements the Pet Owner Reposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerService {

    //Inject PetRepository
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    //Method To Return A List of PetOwners FROM MySQL Database, PetOwner Table
    public List<PetOwner> getPetOwners() {

        return petOwnerRepository.findAll();
    }

    //Save New Pet Owner From NewCustomer Form
    public void save(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
    }

    //Get PetOwner by Id
    public Optional<PetOwner> findById(Long id) {
        return petOwnerRepository.findById(id);
    }

    // Delete PetOwner By ID
    public void delete(Long id) {
        petOwnerRepository.deleteById(id);
    }

}
