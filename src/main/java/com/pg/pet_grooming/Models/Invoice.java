/*
 * 
 * This is the Invoice Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

// Imports
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.FetchType;

// Local Import
import com.pg.pet_grooming.Models.Appointments;
import com.sun.istack.NotNull;

@Entity
@Table(name = "invoice")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {
    
    // Attributes
    @Id
    @Column(name = "invoice_num",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_num;
    
    @NotNull
    @Column(name = "invoice_date",nullable = false)
    private Date invoice_date;
    
     @NotNull
    @Column(name = "invoice_time",nullable = false)
    private Time invoice_time;
    
    // One Invoice for One Appointment
//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name="appointment_id",nullable = false)
//    private Appointments appointment;
    
      @OneToOne(fetch = FetchType.LAZY)
      private Appointments appointment;
}
