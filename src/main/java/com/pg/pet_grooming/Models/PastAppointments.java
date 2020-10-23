 /*
 * Created By Gullian Van Der Walt - 2020/10/04, 12:25
 * Last Updated - 2020/10/04, 12:25
 * 
 */
package com.pg.pet_grooming.Models;

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
@Table(name = "past_appointments")
@Getter
@Setter
@ToString   //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
public class PastAppointments extends Auditable<String> {
    // Appointments Attributes
    // Primary Key

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true,name = "app_date_time",nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date app_date_time;

    @Column(name = "notes")
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
    @JoinTable(name = "past_appointments_pet_services",
            joinColumns = @JoinColumn(name = "p_app_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"))
    private List<Services> services;
    private Integer service_id;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "past_appointments_pet_services",
//            joinColumns = @JoinColumn(name = "p_app_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "app_id"))
//    private List<Appointments> appointments;
//    private Integer app_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pet_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pet pet;
    private Integer pet_id;

    @NotNull
    @Column(name = "pet_name", nullable = false)
    private String pet_name;

    @NotNull
    @Column(name="pet_breed",nullable = false)
    private String pet_breed;  
    
    @NotNull
    @Column(name="employee_id",nullable = false)
    private Integer employee_id;  

}

