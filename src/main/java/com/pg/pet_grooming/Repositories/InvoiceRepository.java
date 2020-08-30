/*
 * This is the InvoiceRepository Interface
   The InvoiceService Class will implement this interface.
 * This interface will extend the JpaRepository interface.
   This will provide services such as CRUD operations
 */
package com.pg.pet_grooming.Repositories;

//Imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Local Imports
import com.pg.pet_grooming.Models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
    
}
