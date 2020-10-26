/*
   Created By Gullian Van Der Walt

 * This is the UserRepository Interface
   The UserLoginService Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

// Imports
import com.pg.pet_grooming.Models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, Integer> {

    UserLogin findByUsername(String username);
    
    // Update user failed attempts
    @Query(value="UPDATE User u SET u.failed_attempt = ?1 WHERE u.username =?2",nativeQuery = true)
    @Modifying
    public void updateFailedAttempt(int failed_attempt, String username);
    
    //SELECT * FROM user WHERE username LIKE 'gullian';
    @Query(value="SELECT * FROM User WHERE username LIKE %:username",nativeQuery = true)
    public UserLogin getUser(String username);
}

