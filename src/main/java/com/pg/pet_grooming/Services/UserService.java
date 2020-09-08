/*
 *  Created By Gullian Van Der Walt
 * 
 *  This is the UserController Class
 */
package com.pg.pet_grooming.Services;

// Imports
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Local Imports
import com.pg.pet_grooming.Repositories.UserRepository;
import com.pg.pet_grooming.Models.UserLogin;


@Service
public class UserService {
    
    // Inject Repository
    @Autowired private UserRepository userRepository;
    
    // Password Encoder
    @Autowired private BCryptPasswordEncoder pEncoder;
    
    public void saveUser(UserLogin user){

       // Encode Password
       user.setPassword(pEncoder.encode(user.getPassword()));
       
       // Save Encrypted Password
       userRepository.save(user);
    }
    
    
}
