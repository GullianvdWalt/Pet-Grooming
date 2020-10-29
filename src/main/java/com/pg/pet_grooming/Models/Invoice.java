/*
 * Created By Gullian Van Der Walt
   Last Updated - 2020/10/13, 09:21
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "invoice")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo for @OneToMany relationship (PetOwner)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice extends Auditable<String>{
    
    // Attributes
    @Id
    @Column(name = "invoice_num",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceNum;
    
    @NotNull
    @Column(name = "invoice_date",nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date invoiceDate;
    
    @Column(name = "invoice_note",nullable = true)
    private String invoiceNote;
    
    @Column(name = "discount",nullable = true)
    private Double discount;
    
    @NotNull
    @Column(name = "total",nullable = false)
    private Double total;
    
    @NotNull
    @Column(name = "payment_method",nullable = false)
    private String paymentMethod;
    
    @Column(name = "pet_owner_full_name",nullable = true)
    private String petOwnerFullName;
    
    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="past_app_id", referencedColumnName = "id",insertable = false, updatable = false)
    private PastAppointments pastAppointment;
    private Integer past_app_id;
    
    @OneToOne(mappedBy="invoice")
    private Income income;
}
