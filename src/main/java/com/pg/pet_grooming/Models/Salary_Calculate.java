/*
 * 
 * This is the Salary_Calculate Model (Entity/Table)    
 * This Model/Class will be used to calculate employee salary
 * The various amounts will be stored in the db for further use in maybe reports
 */
package com.pg.pet_grooming.Models;

// Imports 

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
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
@Table(name = "Salary_Calculate")
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Salary_Calculate {
    
    // Attributes
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(name = "wage",nullable = false)
    private Double wage;
    
    @NotNull
    @Column(name = "num_days",nullable = false)
    private Double numm_days;
    
    @NotNull
    @Column(name = "overtime_amount",nullable = false)
    private Double overtime_amount;
    
    @NotNull
    @Column(name = "overtime_hours",nullable = false)
    private Integer overtime_hours;
    
    @NotNull
    @Column(name = "deduction_total",nullable = false)
    private Double deduction_total;
    
    @NotNull
    @Column(name = "bonus_total",nullable = false)
    private Double bonus_total;
    
    @NotNull
    @Column(name = "subtotal",nullable = false)
    private Double subtotal;
    
    @NotNull
    @Column(name = "total_incl_bonus",nullable = false)
    private Double total_incl_bonus;
    
    @NotNull
    @Column(name = "grand_total",nullable = false)
    private Double grand_total;

    // Default Constructor
    public Salary_Calculate() {
    }
    
    // Getter and Setter Methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Double getNumm_days() {
        return numm_days;
    }

    public void setNumm_days(Double numm_days) {
        this.numm_days = numm_days;
    }

    public Double getOvertime_amount() {
        return overtime_amount;
    }

    public void setOvertime_amount(Double overtime_amount) {
        this.overtime_amount = overtime_amount;
    }

    public Integer getOvertime_hours() {
        return overtime_hours;
    }

    public void setOvertime_hours(Integer overtime_hours) {
        this.overtime_hours = overtime_hours;
    }

    public Double getDeduction_total() {
        return deduction_total;
    }

    public void setDeduction_total(Double deduction_total) {
        this.deduction_total = deduction_total;
    }

    public Double getBonus_total() {
        return bonus_total;
    }

    public void setBonus_total(Double bonus_total) {
        this.bonus_total = bonus_total;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal_incl_bonus() {
        return total_incl_bonus;
    }

    public void setTotal_incl_bonus(Double total_incl_bonus) {
        this.total_incl_bonus = total_incl_bonus;
    }

    public Double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(Double grand_total) {
        this.grand_total = grand_total;
    }

    // Method to calculate deductions total
    public double calcDeductions(){
        
        return deduction_total;
    }
    // Method to calculate bonus total
    public double calcBonus(){
        
        return bonus_total;
    }
   
    public double calcTotal(){
        
        return grand_total;
    }
}
