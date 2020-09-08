/*
 * 
 * This is the Appointments (Entity/Table)
 * 
 */

package com.pg.pet_grooming.Models;

// Imports
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pg.pet_grooming.Models.Invoice;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@Table(name = "appointments")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appointments {
    
    // Appointments Attributes
    // Primary Key
    @Id
    @Column(name = "app_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer app_id;
    
    @NotNull
    @Column(name = "date_created",nullable = false)
    private Date date_created;
    
    @NotNull
    @Column(name = "app_date",nullable = false)
    private Date app_date;
     
    @NotNull
    @Column(name = "start_time",nullable = false)
    private Time start_time;
    
    @NotNull
    @Column(name = "end_time",nullable = false)
    private Time end_time;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "status")
    private String status;
    

    @ManyToOne // One Pet can have many Appointments
    @JoinColumn(name="pet_id", insertable=false, updatable=false)
    private Pet pet; //Pet Object
    private Long pet_id; // Foreign Key
    
//    // One Appointmment, One Invoice
//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "invoice")
//    private Invoice invoice;
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}
