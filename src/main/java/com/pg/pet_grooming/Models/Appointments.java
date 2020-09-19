/*
 * Created By Gullian Van Der Walt - 01/08/2020
   Last Updated - 2020/09/15, 14:12

 * This is the Appointments (Entity/Table)
 * 
 */

package com.pg.pet_grooming.Models;

// Imports
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "appointments")
@Getter
@Setter
@ToString   //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
public class Appointments extends Auditable<String>{
//    
    // Appointments Attributes
    // Primary Key
    @Id
    @Column(name = "app_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer app_id;
    
//    @NotNull
//    @Column(name = "date_created",nullable = false)
//    private Date date_created;
    
    @NotNull
    @Column(name = "app_date",nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date app_date;
     
    @NotNull
    @Column(name = "app_time",nullable = false)
    private int app_time;
    
//    @NotNull
//    @Column(name = "end_time",nullable = false)
//    private Time end_time;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "status")
    private String status;

    // Appointments
    // Pet - Appointment_Pets - Appointments
    
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name="appointments_pets",
    joinColumns = @JoinColumn(name="app_id",referencedColumnName = "app_id"),
    inverseJoinColumns = @JoinColumn(name="pet_id", referencedColumnName = "id"))
    private List<Pet> petList;

    @NotNull
    @Column(name = "pet_owner_id",nullable = false)
    private int pet_owner_id;
    
    // Services
    // Appointment - Services - Appointment_Pet_Services - Pet
    
    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name="appointments_pet_services",
    joinColumns = @JoinColumn(name="app_id",referencedColumnName = "app_id"),
    inverseJoinColumns = @JoinColumn(name="service_id", referencedColumnName = "service_id"))
    private List<Services> services;

    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name="appointments_pet_services",
    joinColumns = @JoinColumn(name="app_id",referencedColumnName = "app_id"),
    inverseJoinColumns = @JoinColumn(name="pet_id", referencedColumnName = "id"))
    private List<Pet> pet;
    

    
    // Constructor handled by Lombok
    // Getters and Setters handled by lombok
    
}
