 /*
 * Created By Gullian Van Der Walt - 01/08/2020
   Last Updated - 20/09/07, 04:51
 * This is the Services (Entity/Table)
 * 
 */

package com.pg.pet_grooming.Models;

// Imports
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "services")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Services extends Auditable<String>{
   
    // Services Attributes
    
    // Primary Key
    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_id;
    
    @Column(name = "service_name", length = 255,nullable = false)
    private String service_name;
    
    @Column(name = "service_icon", length = 255,nullable = true)
    private String service_icon;
    
    @Column(name = "service_price", length = 255,nullable = false)
    private double service_price;
    
    @Column(name = "service_status",nullable = false)
    private boolean service_status;
    

}
