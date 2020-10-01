/*
 * 
 * This is the EmployeeBenefits Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

//Imports

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_benefits")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor   
public class EmployeeBenefits {
    
    // Attributes
    @Id
    @Column(name = "benefit_id", nullable = false)
    private Integer benefit_id;
    
//    @ManyToOne
//    @JoinColumn(name="employee_id", insertable=false, updatable=false)
//    private Employees employee;
//    private long employee_id;
//    private String employee_name;
    
    @NotNull
    @Column(name = "description",nullable = false)
    private String description;
    
    @NotNull
    @Column(name = "amount",nullable = false)
    private double amount;
    
}
