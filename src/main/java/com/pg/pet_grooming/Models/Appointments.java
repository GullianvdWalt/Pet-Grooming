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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
// Local Imports

@Entity
@Table(name = "appointments")
@Getter
@Setter
@ToString   //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appointments extends Auditable<String>{
    
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
    private Date app_date;
     
    @NotNull
    @Column(name = "app_time",nullable = false)
    private int appTime;
    
//    @NotNull
//    @Column(name = "end_time",nullable = false)
//    private Time end_time;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "status")
    private String status;

    @NotNull
    @ManyToOne // One Pet can have many Appointments
    @JoinColumn(name="pet_id", insertable=false, updatable=false)
    @JsonBackReference
    private Pet pet; //Pet Object
    private int pet_id; // Foreign Key
    
    @NotNull
    @Column(name = "pet_owner_id",nullable = false)
    private int pet_owner_id;
    
    @NotNull
    @Column(name="pet_owner_cell",length =15,nullable = false)
    private String pet_owner_cell;
    
    @NotNull
    @Column(name="pet_owner_address")
    private String pet_owner_address;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name="appointment_services",joinColumns = 
            {@JoinColumn(name="app_id")},
            inverseJoinColumns = {@JoinColumn(name ="service_id")})
    private List<Services> services;
    
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
}
