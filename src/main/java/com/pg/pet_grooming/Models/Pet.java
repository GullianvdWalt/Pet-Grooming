/*
 * Created By Gullian Van Der Walt - 01/08/2020
   Last Updated - 20/09/07, 04:51
 * This is the PetOwner Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

// Imports
import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pet")
@Getter
@Setter
@ToString
@NoArgsConstructor          
@AllArgsConstructor         
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pet extends Auditable<String>{
    
    // Attributes
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    

    @ManyToOne
    @JoinColumn(name="pet_owner_id",insertable=false, updatable=false)
    @JsonBackReference
    private PetOwner petOwner; //Pet Owner Object
    private Integer pet_owner_id;
    
    @NotNull
    @Column(name = "pet_name", length = 255,nullable = false)
    private String pet_name;
    
    @NotNull
    @Column(name = "pet_gender", length = 1)
    private String pet_gender;
                 
    @NotNull
    @Column(name = "pet_breed", length = 255,nullable = false)
    private String pet_breed;
    
    @NotNull
    @Column(name = "pet_size", length = 15,nullable = false)
    private String pet_size;
    
    
    @Column(name = "pet_notes")
    private String pet_notes;
    
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}
