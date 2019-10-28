package com.company.shippingservice.dao;

import com.company.shippingservice.dto.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    /**
     *Custom getById method defined in order to have a method that does not return an optional.
     */
    Invoice getByInvoiceId(int id);

}
