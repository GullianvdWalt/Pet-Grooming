/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
*/
package com.pg.pet_grooming.Models;

// Imports
import com.sun.istack.NotNull;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
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
public class Appointments extends Auditable<String> {

    // Appointments Attributes
    // Primary Key
    @Id
    @Column(name = "app_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer app_id;

    @NotNull
    @Column(unique = true,name = "app_date_time",nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date appDateTime;

    @Column(name = "notes", columnDefinition = "TEXT default 'None'")
    private String notes;
  
    @NotNull
    @Column(name = "pet_owner_id", nullable = false)
    private int pet_owner_id;

    @NotNull
    @Column(name = "pet_owner_full_name", nullable = false)
    private String pet_owner_full_name;

    @NotNull
    @Column(name = "pet_owner_cell", nullable = false)
    private String pet_owner_cell;

    @NotNull
    @Column(name = "pet_owner_address", nullable = false)
    private String pet_owner_address;

    // Services
    // Appointment - Services - Appointment_Pet_Services - Pet
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "appointments_pet_services",
            joinColumns = @JoinColumn(name = "app_id", referencedColumnName = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"))
    private List<Services> services;
    private Integer service_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pet_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pet pet;
    private Integer pet_id;

    @NotNull
    @Column(name = "pet_name", nullable = false)
    private String pet_name;

    @NotNull
    @Column(name = "pet_breed", nullable = false)
    private String pet_breed;

    // Constructor handled by Lombok
    // Getters and Setters handled by lombok
}

