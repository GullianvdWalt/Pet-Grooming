/*
 * 
 * This the Appointment_Services (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment_services")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appointment_Services {

    // Appointments Services Attributes
    // Primary Key
    @Id
    @Column(name = "app_service_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long app_service_id;

    @ManyToOne // Many Appointment Services to one appointment
    @JoinColumn(name = "app_id", insertable = false, updatable = false)
    private Appointments appointment; //Pet Object
    private Long app_id; // Foreign Key

    @ManyToOne // Many services to one pet
    @JoinColumn(name = "pet_id", insertable = false, updatable = false)
    private Pet pet; //Pet Object
    private Long pet_id; // Foreign Key

    @Column(name = "trim")
    private Boolean trim;
    @Column(name = "nail_cut")
    private Boolean nail_cut;
    @Column(name = "shave")
    private Boolean shave;
    @Column(name = "bath")
    private Boolean bath;
    @Column(name = "dip")
    private Boolean dip;

}
