/*
 * This is the UserRepository Interface
   The UserLoginService Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

// Imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Local Imports
import com.pg.pet_grooming.Models.UserLogin;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, Integer>{
    
    UserLogin findByUsername(String username);
    
}
