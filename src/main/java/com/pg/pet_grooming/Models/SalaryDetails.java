/*
 * Created By Gullian Van Der Walt - 2020/10/04, 14:47
 * Last Updated - 2020/10/04, 14:48
 * 
 */
package com.pg.pet_grooming.Models;

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
@Table(name = "SalaryDetails")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SalaryDetails extends Auditable<String>{
    // Attributes
    
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Basic wage per hour
   @NotNull
   @Column(name = "wage",nullable = false)
   private Integer wage;
   
   @NotNull
   @Column(name = "overtime",nullable = false)
   private Integer overtime;
   
   // Bonus
   @NotNull
   @Column(name = "bonus_large",nullable = false)
   private Integer bonus_large;
   
   // Bonus
   @NotNull
   @Column(name = "bonus_medium",nullable = false)
   private Integer bonus_medium;
   
   // Bonus
   @NotNull
   @Column(name = "bonus_small",nullable = false)
   private Integer bonus_small;
   
   @NotNull
   @Column(name = "transport_amount",nullable = false)
   private Integer transport_amount;
   
}
