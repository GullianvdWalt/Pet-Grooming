/*
 * This is the InvoiceService Class
 * This Class implements the InvoiceReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Local Imports
import com.pg.pet_grooming.Models.Invoice;
import com.pg.pet_grooming.Repositories.InvoiceRepository;

@Service
public class InvoiceService {
    
// Inject Repository 
@Autowired private InvoiceRepository invoiceRepository;    

    public List<Invoice> getInvoices(){
        return invoiceRepository.findAll();
    }

    public void save(Invoice invoice){
        invoiceRepository.save(invoice);
    }

    public Optional<Invoice> findById(Integer id){
        return invoiceRepository.findById(id);
    }
    
      
    public void delete(Integer id) {
        invoiceRepository.deleteById(id);
    }
}
