/*
    Gullian Van Der Walt
    Data Transger Object Class to help with sorting of expenses by Month
 */
package com.pg.pet_grooming.DAO;


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
public class ExpensesByMonth {
    
   private int mn;
   private int yr;
   private double total;
  
}
