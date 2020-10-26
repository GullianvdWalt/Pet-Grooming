/*
 * Created By Gullian Van Der Walt - 01/08/2020
   Last Updated - 20/09/07, 04:51
 * This is the PetOwner Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
public class PetOwner extends Auditable<String> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "pet_owner_full_name", length = 100, nullable = false)
    private String petOwnerFullName;

    @NotNull
    @Size
    @Column(name = "pet_owner_cell", length = 20, nullable = false)
    private String petOwnerCell;

    @NotNull
    @Column(name = "pet_owner_address", length = 255, nullable = false)
    private String petOwnerAddress;

    @NotNull
    @Column(name = "pet_owner_city", length = 55, nullable = false)
    private String petOwnerCity;

    @OneToMany(mappedBy = "petOwner", fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Pet> pets;

    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}

