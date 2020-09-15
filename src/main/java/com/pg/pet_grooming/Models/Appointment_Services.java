 /*
 * Created By Gullian Van Der Walt - 01/08/2020
   Last Updated - 2020/09/15, 14:12

 * This is the Appointment_Services (Entity/Table)
   Used to Join Appointments and Services
 * 
 */


package com.pg.pet_grooming.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer app_service_id;
    
    @ManyToOne // Many Appointment Services to one appointment
    @JoinColumn(name="app_id", insertable=false, updatable=false)
    private Appointments appointment;
    private Long app_id; // Foreign Key
    
    @ManyToOne // Many services to one pet
    @JoinColumn(name="pet_id", insertable=false, updatable=false)
    @JsonBackReference
    private List<Pet> pets;
    
    private List<Services> services;

    
}
