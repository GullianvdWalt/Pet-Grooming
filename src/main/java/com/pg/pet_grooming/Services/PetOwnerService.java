/* Create By Gullian Van Der Walt 15/07/2020
   Last Updated 08/09/2020
 * This is the Pet Owner Service Class
 * This Class implements the Pet Owner Reposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Controllers.ResourceNotFoundException;
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

    //Get PetOwner by Id as Optional List
    public Optional<PetOwner> findById(Integer id) {
        return petOwnerRepository.findById(id);
    }

    // Get PetOwnerbyID and retunr as object
    public PetOwner findPetOwnerById(Integer id) throws ResourceNotFoundException {
        Optional<PetOwner> petOwner = petOwnerRepository.findById(id);
        if (petOwner.isPresent()) {
            return petOwner.get();
        } else {
            throw new ResourceNotFoundException("No Pet Owner was found by for given id");
        }
    }
    
    // Delete PetOwner By ID
    public void delete(Integer id) {
        petOwnerRepository.deleteById(id);
    }

    // Get PetOwner By Keyword from SQL Query Defined in the PetOwnerRepository
    public List<PetOwner> findPetOwnerByKeyword(String keyword) {
        return petOwnerRepository.findByKeyword(keyword);
    }

    // Create or Update Pet Owner
    public PetOwner createOrUpdatePetOwner(PetOwner petOwner) {
        // New PetOwner
        if (petOwner.getId() == null) {
            petOwner = petOwnerRepository.save(petOwner);
            return petOwner;
        } else {
            // Update
            Optional<PetOwner> petOwnerEntity = petOwnerRepository.findById(petOwner.getId());
            if (petOwnerEntity.isPresent()) {
                PetOwner newPetOwner = petOwnerEntity.get();
                newPetOwner.setPet_owner_full_name(petOwner.getPet_owner_full_name());
                newPetOwner.setPet_owner_cell(petOwner.getPet_owner_cell());
                newPetOwner.setPet_owner_address(petOwner.getPet_owner_address());

                newPetOwner = petOwnerRepository.save(newPetOwner);

                return newPetOwner;
            } else {
                petOwner = petOwnerRepository.save(petOwner);

                return petOwner;
            }

        }

    }
}

