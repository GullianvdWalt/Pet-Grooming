/*
   Created By Gullian Van Der Walt

 * This Is The UserLogin Login Class
 */
package com.pg.pet_grooming.Models;

//Imports
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
//Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
public class UserLogin {

    // UserLogin Attributes
    // Primary Key
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
   
    @Column(name = "failed_attempt", nullable = true,columnDefinition = "TINYINT",length = 4)
    private int failed_attempt;
    @Column(name = "account_non_locked",nullable = true,columnDefinition = "TINYINT",length = 4)
    private boolean account_non_locked;
    @Column(name = "lock_time", nullable = true)
    private Date lock_time;
    
}

