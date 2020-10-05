/*
 * 
 * This is the Employees Model (Entity/Table)
 * 
 */
package com.pg.pet_grooming.Models;

import com.sun.istack.NotNull;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor      
public class Employees extends Auditable<String>{
    
    // Attributes
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Size(min = 13, max = 13)
    @Column(name = "emp_sa_id",nullable = false)
    private Long emp_sa_id;
    
    @NotNull
    @Column(name = "employee_full_name", length = 255,nullable = false)
    private String employee_full_name;
    
    @NotNull
    @Column(name = "occupation", length = 255,nullable = false)
    private String occupation;
    
    @NotNull
    @Column(name = "date_hired",nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_hired;
    
    @NotNull
    @Column(columnDefinition="VARCHAR(15)",name = "cell",nullable = false)
    private String cell;
    
    
    
    // Constructor handled by Lombok
    // Getters and Setters handled by Lombok
    // toString Method handled by Lombok
}
