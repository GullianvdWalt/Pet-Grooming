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

    // No Args Constructor
    public Pet() {
    }
    // All Args Constructor
    public Pet(Integer id, PetOwner petOwner, Integer pet_owner_id, 
            String pet_name, String pet_gender, String pet_breed, 
            String pet_size, String pet_notes) {
        this.id = id;
        this.petOwner = petOwner;
        this.pet_owner_id = pet_owner_id;
        this.pet_name = pet_name;
        this.pet_gender = pet_gender;
        this.pet_breed = pet_breed;
        this.pet_size = pet_size;
        this.pet_notes = pet_notes;
    }
    // Getter And Setter Methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwner petOwner) {
        this.petOwner = petOwner;
    }

    public Integer getPet_owner_id() {
        return pet_owner_id;
    }

    public void setPet_owner_id(Integer pet_owner_id) {
        this.pet_owner_id = pet_owner_id;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getPet_gender() {
        return pet_gender;
    }

    public void setPet_gender(String pet_gender) {
        this.pet_gender = pet_gender;
    }

    public String getPet_breed() {
        return pet_breed;
    }

    public void setPet_breed(String pet_breed) {
        this.pet_breed = pet_breed;
    }

    public String getPet_size() {
        return pet_size;
    }

    public void setPet_size(String pet_size) {
        this.pet_size = pet_size;
    }

    public String getPet_notes() {
        return pet_notes;
    }

    public void setPet_notes(String pet_notes) {
        this.pet_notes = pet_notes;
    }

    // To String
    
        @Override
    public String toString() {
        return "Pet{" + "id=" + id + ", petOwner=" + petOwner + 
                ", pet_owner_id=" + pet_owner_id + ", pet_name=" + pet_name +
                ", pet_gender=" + pet_gender + ", pet_breed=" + pet_breed +
                ", pet_size=" + pet_size + ", pet_notes=" + pet_notes + '}';
    }
    // Override Methods
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        Pet other = (Pet) obj;
        if(id == null){
            if(other.id != null){
                return false;
            }   
        }else if(!id.equals(other.id)){
            return false;
        }
        return true;
    }
    
    
}
