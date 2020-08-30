/*
 * 
 * This is the Employees Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.sun.istack.NotNull;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data  //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor      
public class Employees {
    
    // SA ID of Employee is Used to identify
    @Id
    @Column(columnDefinition="VARCHAR(15)",name = "employee_id", length = 15,nullable = false)
    private String employee_id;
    
    @NotNull
    @Column(name = "employee_full_name", length = 255,nullable = false)
    private String employee_full_name;
    
    @NotNull
    @Column(name = "occupation", length = 255,nullable = false)
    private String occupation;
    
    @NotNull
    @Column(name = "date_hired",nullable = false)
    private Date date_hired;
    
    @NotNull
    @Column(columnDefinition="VARCHAR(15)",name = "cell",nullable = false)
    private String cell;
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
    
}
