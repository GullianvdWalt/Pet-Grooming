/*
*  Created By Gullian Van Der Walt - 2020/09/14, 11:22
   Last Updated - 2020/09/14, 11:22s
*
*/
package com.pg.pet_grooming.Models;

import com.sun.istack.NotNull;
import java.time.Duration;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/*
 * 
 * This is the BusinessDetails Model (Entity/Table)
 * 
 */

@Entity
@Table(name = "business_details")
@Getter
@Setter
@ToString   //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
public class BusinessDetails extends Auditable<String>{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
       
    // 24 Format time will be implemented
    
    @NotNull
    @Column(name = "work_hours_start",nullable = false)
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date work_hours_start;
    
    @NotNull
    @Column(name = "work_hours_end",nullable = false)
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date work_hours_end;
    
        
    @NotNull
    @Column(name = "total_work_hours",nullable = false)
    private int total_work_hours;
    
    
    @NotNull
    @Column(name = "appointment_length",nullable = false)
    private int appointment_length;
    
    @NotNull
    @Column(name = "business_name",nullable = false)
    private String business_name;
    
  
    @Column(name = "logo",nullable = true)
    private String logo;
    
    @Transient
    public String getImagePath(){
        if(logo == null || id == null) return null;
        
        return "/Icon/" + id + "/" + logo;
    }
}
