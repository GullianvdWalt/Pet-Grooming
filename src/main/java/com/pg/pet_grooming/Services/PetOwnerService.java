/*
 * This is the Pet Owner Service Class
 * This Class implements the Pet Owner Reposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

import com.pg.pet_grooming.Repositories.PetOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerService {
    //Inject PetRepository
    @Autowired
    private PetOwnerRepository petOwnerRepository;
    

}
