/*
 * 
 * This is the Salary_Calculate Model (Entity/Table)    
 * This Model/Class will be used to calculate employee salary
 * The various amounts will be stored in the db for further use in maybe reports
 */
package com.pg.pet_grooming.Models;

// Imports 

public class Salary_Calculate {
    
    

    private Double wage;
    private Double numm_days;
    private Double overtime_amount;
    private int overtime_hours;
    private Double deduction_total;
    private Double bonus_total;
    private Double subtotal;
    private Double total_incl_bonus;
    private Double grand_total;

    // Default Constructor
    public Salary_Calculate() {
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
