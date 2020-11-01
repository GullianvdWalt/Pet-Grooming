/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.UserLogin;
import com.pg.pet_grooming.Models.UserLoginPrincipal;
import com.pg.pet_grooming.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // UserLogin Model object
        UserLogin userLogin = userLoginRepository.findByUsername(username);
        // If there is not user
        if (userLogin == null) {

            throw new UsernameNotFoundException("User not found!");
        }

        return new UserLoginPrincipal(userLogin);
    }

}

