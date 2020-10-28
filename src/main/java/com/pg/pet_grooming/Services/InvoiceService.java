/*
 * This is the InvoiceService Class
 * This Class implements the InvoiceReposistory 
 * The controller sends request to this class
 */
package com.pg.pet_grooming.Services;

// Imports
import com.pg.pet_grooming.Models.Invoice;
import com.pg.pet_grooming.Repositories.InvoiceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

// Inject Repository 
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Page<Invoice> getInvoices(int pageNum, String sortField, String sortDir) {
        //Paging
        Pageable pageable = PageRequest.of(pageNum - 1, 6, 
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
			: Sort.by(sortField).descending()
	);
        return invoiceRepository.findAll(pageable);
    }

    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    public Optional<Invoice> findById(Integer id) {
        return invoiceRepository.findById(id);
    }

    public void delete(Integer id) {
        invoiceRepository.deleteById(id);
    }
}

