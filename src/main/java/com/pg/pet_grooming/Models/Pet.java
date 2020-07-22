/*
 * 
 * This is the Pet Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pet")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
public class Pet {
    @Id
    @Column(name = "petID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petID;
    
    @OneToMany // One Owner can have many pets.
    @JoinColumn(name="pet_owner_ID", insertable=false, updatable=false)
    private PetOwner petOwner; //Pet Owner Object
    private Long pet_owner_ID;
    
    @NotNull
    @Column(name = "pet_name", length = 255)
    private String petName;
    
    @NotNull
    @Column(name = "pet_gender", length = 1)
    private String pet_gender;
                 
    @NotNull
    @Column(name = "pet_breed", length = 255)
    private String pet_breed;
    
    @NotNull
    @Column(name = "pet_size", length = 15)
    private String pet_size;
    
    
    @Column(name = "pet_notes")
    private String pet_notes;
    
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}
