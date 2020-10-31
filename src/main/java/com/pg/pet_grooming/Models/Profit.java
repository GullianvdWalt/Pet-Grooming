/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Models;

// Imports
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
// Local Imports


@Entity
@Table(name = "profit")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Profit extends Auditable<String>{
        
    // Attributes
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "start_date",nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date startDate;

    @Column(name = "end_date",nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date endDate;
    
    @NotNull
    @Column(name = "income_total",nullable = false)
    private Double incomeTotal;
    
    @NotNull
    @Column(name = "expense_total",nullable = false)
    private Double expenseTotal;
    
    @NotNull
    @Column(name = "salary_total",nullable = false)
    private Double salaryTotal;
    
    @NotNull    
    @Column(name = "total_before_salary_deduct",nullable = false)
    private Double totalBfrSalDeduct;
       
    @NotNull
    @Column(name = "grand_total",nullable = false)
    private Double grandTotal;


}
