/*
 * 
 * This is the PetOwner Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pet_owner")
@Data  //Lombok, Adds Getters, Setters and ToString Methodss
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (Pet)
public class PetOwner {
    
    @Id
    @Column(name = "pet_owner_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petOwnerID;
    
    @NotNull
    @Column(name = "pet_owner_full_name", length = 100)
    private String pet_owner_full_name;
    
    @NotNull
    @Column(name = "pet_owner_cell", length = 20)
    private String pet_owner_cell;
    
    @NotNull
    @Column(name = "pet_owner_address", length = 255)
    private String pet_owner_address;
    
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}
