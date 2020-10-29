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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Salaries")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Salaries {

    // Attributes
    @Id
    @Column(name = "salary_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salaryId;

    @NotNull
    @Column(name = "pay_period_start", nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date pay_period_start;

    @NotNull
    @Column(name = "pay_period_end", nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date pay_period_end;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "total_days")
    private Integer total_days;

    @NotNull
    @Column(name = "payment_method", nullable = false)
    private String payment_method;

    @Column(name = "overtime_hours", nullable = true)
    private Integer overtime_hours;

    @Column(name = "overtime_total", nullable = true)
    private Double overtimeTotal;

    @Column(name = "deduction_total", nullable = true)
    private Double deduction_total;

    @Column(name = "deduction_description", nullable = true)
    private String deduction_description;

    @Column(name = "transport_total", nullable = true)
    private Double transport_total;

    @Column(name = "numLDog", nullable = true)
    private Integer numLDog;

    @Column(name = "numMDog", nullable = true)
    private Integer numMDog;

    @Column(name = "numSDog", nullable = true)
    private Integer numSDog;

    @Column(name = "amountLDog", nullable = true)
    private Integer amountLDog;

    @Column(name = "amountMDog", nullable = true)
    private Integer amountMDog;

    @Column(name = "amountSDog", nullable = true)
    private Integer amountSDog;

    @Column(name = "bonus_total", nullable = true)
    private Double bonusTotal;

    @NotNull
    @Column(name = "salary_subtotal", nullable = false)
    private Double salary_subtotal;

    @Column(name = "salary_total_w_bonus", nullable = true)
    private Double salary_total_w_bonus;

    @Column(name = "salary_total_aftr_deduct", nullable = true)
    private Double salary_total_aftr_deduct;

    @NotNull
    @Column(name = "salary_grand_total", nullable = false)
    private Double salaryGrandTotal;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employees employee;
    private Integer employee_id; // Foreign Key

    @NotNull
    @Column(name = "employee_full_name", nullable = false)
    private String employeeFullName;
}

