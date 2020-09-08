/*
 * 
 * This is the Expenses Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

// Imports
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import java.util.Date;
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
@Table(name = "expenses")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Expenses {
    
    // Attributes
    @Id
    @Column(name = "expense_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expense_id;
    
    @Column(name = "expense_date",nullable = false)
    private Date expense_date;
    
    @Column(name = "expense_type",nullable = false)
    private String expense_type;
    
    @Column(name = "expense_description",nullable = false)
    private String expense_description;
    
    @Column(name = "expense_amount",nullable = false)
    private Double expense_amount;
    
}
