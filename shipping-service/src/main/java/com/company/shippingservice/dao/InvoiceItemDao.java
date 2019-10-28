package com.company.shippingservice.dao;

import com.company.shippingservice.dto.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemDao extends JpaRepository<InvoiceItem,Integer> {


    /**
     *Custom getById method defined in order to have a method that does not return an optional.
     */
    InvoiceItem getByInvoiceItemId(int id);

}
