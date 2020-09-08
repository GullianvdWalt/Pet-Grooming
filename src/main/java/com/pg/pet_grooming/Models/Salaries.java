/*
 * 
 * This is the Salaries Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

// Imports

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "Salaries")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Salaries {
    
    // Attributes
    @Id
    @Column(name = "salary_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salary_id;
    
    @NotNull
    @Column(name = "pay_period",nullable = false)
    private String pay_period;
    
    @NotNull
    @Column(name = "payment_date",nullable = false)
    private Date date;
    
    @NotNull
    @Column(name = "payment_method",nullable = false)
    private String payment_method;
        
    @NotNull
    @Column(name = "payment_freq",nullable = false)
    private String payment_freq;
    
    @NotNull
    @Column(name = "salary_total",nullable = false)
    private Double salary_total;
    

    @OneToOne(fetch = FetchType.LAZY)
    private Salary_Calculate salary_calculate;
    
    @ManyToOne 
    @JoinColumn(name="employee_id", insertable=false, updatable=false)
    private Employees employee;
    private Long employee_id; // Foreign Key
    
}
