/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.UserLogin;
import com.pg.pet_grooming.Repositories.UserRepository;
import java.nio.file.attribute.UserPrincipal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    // Number of attempts for user to login
    public static final int MAX_FAILED_ATTEMPTS = 3;
    // Account Lock time H * M * S * MS
    private static final long LOCK_TIME_DURATION = 5 * 60 * 1000; // 5 Min
    
    // Inject Repository
    @Autowired private UserRepository userRepository;

    // Password Encoder
    @Autowired
    private BCryptPasswordEncoder pEncoder;

    public void saveUser(UserLogin user) {

        // Encode Password
        user.setPassword(pEncoder.encode(user.getPassword()));

        // Save Encrypted Password
        userRepository.save(user);
    }

    public UserLogin getUserByUsername(String username){
        return userRepository.getUser(username);
    }

    public void increaseFailedAttempt(UserLogin user) {
        int newFailedAttempts = user.getFailed_attempt() + 1;
        userRepository.updateFailedAttempt(newFailedAttempts,user.getUsername());
    }

    public void lock(UserLogin user) {
        user.setAccount_non_locked(false);
        user.setLock_time(new Date());
        userRepository.save(user);
    }
    // Unlock Account
    public boolean unlock(UserLogin userLogin){
        long lockTimeInMillis = userLogin.getLock_time().getTime();
        long currentTimeInMillis = System.currentTimeMillis();
        // Unlock User
        if(lockTimeInMillis + LOCK_TIME_DURATION > currentTimeInMillis){
            userLogin.setLock_time(null);
            userLogin.setFailed_attempt(0);
            userRepository.save(userLogin);
            return true;
        }
        return false;
    }

    public void resetFailedAttempts(String username) {
        userRepository.updateFailedAttempt(0, username);
                
    }
                
}

