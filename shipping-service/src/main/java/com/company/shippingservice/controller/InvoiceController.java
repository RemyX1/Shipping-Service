package com.company.shippingservice.controller;

import com.company.shippingservice.dto.Invoice;
import com.company.shippingservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {


    /**
     *Autowired ServiceLayer for use within controller
     */
    @Autowired
    ServiceLayer service;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public Invoice createInvoice(@RequestBody Invoice invoice) {

        return service.createInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/get/{id}", method = RequestMethod.GET)
    public Invoice getInvoice(@PathVariable int id) {
        return service.getInvoice(id);
    }


    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices() {
        return service.getAllInvoices();
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    public void updateInvoice(@RequestBody Invoice invoice) {

        service.updateInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/delete/{id}", method = RequestMethod.DELETE)
    public void deleteinvoice(@PathVariable int id) {
        service.deleteInvoice(id);
    }

}
