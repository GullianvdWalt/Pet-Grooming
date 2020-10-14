/*
 * 
 * This is the Pet Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pet")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pet {

    // Attributes
    @Id
    @Column(name = "pet_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petID;

    @ManyToOne // Many Pets have one owner
    @JoinColumn(name = "pet_owner_id", insertable = false, updatable = false)
    private PetOwner petOwner; //Pet Owner Object
    private Long pet_owner_id;

    @NotNull
    @Column(name = "pet_name", length = 255, nullable = false)
    private String petName;

    @NotNull
    @Column(name = "pet_gender", length = 1)
    private String pet_gender;

    @NotNull
    @Column(name = "pet_breed", length = 255, nullable = false)
    private String pet_breed;

    @NotNull
    @Column(name = "pet_size", length = 15, nullable = false)
    private String pet_size;

    @Column(name = "pet_notes")
    private String pet_notes;

    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}
