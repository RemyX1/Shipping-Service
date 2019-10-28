package com.company.shippingservice.controller;

import com.company.shippingservice.dto.InvoiceItem;
import com.company.shippingservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceItemController {

    @Autowired
    ServiceLayer service;

    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)
    public InvoiceItem createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        return service.createInvoiceItem(invoiceItem);
    }

    @RequestMapping(value = "/invoiceItem/get/{id}", method = RequestMethod.GET)
    public InvoiceItem getInvoiceItem(@PathVariable int id) {
        return service.getInvoiceItem(id);
    }


    @RequestMapping(value = "/invoiceItem", method = RequestMethod.GET)
    public List<InvoiceItem> getAllInvoiceItems() {
        return service.getAllInvoiceItems();
    }

    @RequestMapping(value = "/invoiceItem", method = RequestMethod.PUT)
    public void updateInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        service.updateInvoiceItem(invoiceItem);
    }

    @RequestMapping(value = "/invoiceItem/delete/{id}", method = RequestMethod.DELETE)
    public void deleteinvoiceItem(@PathVariable int id) {
        service.deleteInvoiceItem(id);
    }


}
