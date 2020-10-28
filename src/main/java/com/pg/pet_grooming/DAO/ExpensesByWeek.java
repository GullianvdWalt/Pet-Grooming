/*
    Gullian Van Der Walt
    Data Transger Object Class to help with sorting of expenses by Week
 */
package com.pg.pet_grooming.DAO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesByWeek {
    
   private Date startDate; 
   private int wk; 
   private int mn;
   private int yr;
   private double total; 
}
