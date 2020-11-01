/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020

 * This is the Appointments_Pet_Services (Entity/Table)
   Used to Join Appointments and Services
 * 
 */


package com.pg.pet_grooming.Models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "past_appointments_pet_services")
@Getter
@Setter
@ToString
@NoArgsConstructor 
@AllArgsConstructor 
public class Past_Appointments_Pet_Services {
       
    // Primary Key
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Past Appointments    
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="p_app_id", referencedColumnName = "id",insertable = false, updatable = false)
    private PastAppointments pastAppointments;
    private Integer p_app_id;
    

    // Services
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="service_id",insertable = false, updatable = false)
    private Services service;
    private Integer service_id;


    
    
}