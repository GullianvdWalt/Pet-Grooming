/*
*   © Pet Grooming
    © Gullian Van Der Walt
*   Pearson Pretoria ITSP300 - Project 2020
 */
package com.pg.pet_grooming.Repositories;

//Imports
import com.pg.pet_grooming.Models.Invoice;
import com.pg.pet_grooming.Models.Salaries;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    // Search Invoices
    // SQL Search Query
    @Query(value = "SELECT * FROM Invoice i WHERE i.invoice_num LIKE %:keyword%"
            + " OR i.invoice_date LIKE %:keyword% OR i.payment_method LIKE %:keyword%"
            + " OR i.total LIKE %:keyword% OR i.pet_owner_full_name LIKE %:keyword%", nativeQuery = true)
   List<Invoice> findByKeyword(@Param("keyword") String keyword);
}

