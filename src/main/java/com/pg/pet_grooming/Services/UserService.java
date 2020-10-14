/*
 *  Created By Gullian Van Der Walt
 * 
 *  This is the UserController Class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.UserLogin;
import com.pg.pet_grooming.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Inject Repository
    @Autowired
    private UserRepository userRepository;

    // Password Encoder
    @Autowired
    private BCryptPasswordEncoder pEncoder;

    public void saveUser(UserLogin user) {

        // Encode Password
        user.setPassword(pEncoder.encode(user.getPassword()));

        // Save Encrypted Password
        userRepository.save(user);
    }

}

