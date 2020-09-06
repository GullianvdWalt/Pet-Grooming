/*
 * 
 * This is the PetOwner Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pet_owner")
@Getter
@Setter
@ToString
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @ManyToOne relationship (Pet)
public class PetOwner extends Auditable<String>{
    
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @NotNull
    @Column(name = "pet_owner_full_name", length = 100,nullable = false)
    private String pet_owner_full_name;
    
    @NotNull
    @Column(name = "pet_owner_cell", length = 20,nullable = false)
    private String pet_owner_cell;
    
    @NotNull
    @Column(name = "pet_owner_address", length = 255,nullable = false)
    private String pet_owner_address;
    
    @OneToMany(mappedBy = "petOwner", cascade = CascadeType.ALL)
    private List<Pet> pets;
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}
