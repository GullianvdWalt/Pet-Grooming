/*
 * 
 * This is the CashCount Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

// Imports

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.sql.Date;
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
@Table(name = "cashcount")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cash_Count {
        // Attributes
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Date date;
    private int note200;
    private int note100;
    private int note50;
    private int note10;
    private int coin5;
    private int coin2;
    private int coin1;
    
}
