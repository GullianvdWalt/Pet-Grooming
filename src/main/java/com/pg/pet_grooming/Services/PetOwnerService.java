/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Controllers.ResourceNotFoundException;
import com.pg.pet_grooming.Models.PetOwner;
import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class PetOwnerService {

    //Inject PetRepository
    @Autowired private PetOwnerRepository petOwnerRepository;

    //Method To Return A List of PetOwners FROM MySQL Database, PetOwner Table
    public Page<PetOwner> getPetOwners(int pageNum, String sortField, String sortDir) {
        // Paging
        Pageable pageable = PageRequest.of(pageNum - 1, 6, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
		
        
        return petOwnerRepository.findAll(pageable);
    }
    
    // New Appointment - Select Pet
    //Method To Return A List of PetOwners FROM MySQL Database, PetOwner Table
    public Page<PetOwner> getPetPetOwners(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
        return petOwnerRepository.findAll(pageable);
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
                newPetOwner.setPetOwnerFullName((petOwner.getPetOwnerFullName()));
                newPetOwner.setPetOwnerCell(petOwner.getPetOwnerCell());
                newPetOwner.setPetOwnerAddress(petOwner.getPetOwnerAddress());
                newPetOwner = petOwnerRepository.save(newPetOwner);

                return newPetOwner;
            } else {
                petOwner = petOwnerRepository.save(petOwner);

                return petOwner;
            }

        }

    }
}

