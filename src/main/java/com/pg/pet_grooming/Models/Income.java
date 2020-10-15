/*
    Created By Gullian Van Der Walt - 2020/10/13, 06:27
    Entity Class to store income
 */
package com.pg.pet_grooming.Models;


import com.sun.istack.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "income")
@Getter
@Setter
@ToString   //Lombok, Adds Getters, Setters and ToString Methods
@NoArgsConstructor //Lombok, Adds The Default Constructor
@AllArgsConstructor         //JsonIdentityInfo 
public class Income extends Auditable<String>{
    
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(name = "amount",nullable = false)
    private Double amount;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_num", referencedColumnName = "invoice_num",insertable = false, updatable = false)
    private Invoice invoice;
    private Integer invoice_num;
}
