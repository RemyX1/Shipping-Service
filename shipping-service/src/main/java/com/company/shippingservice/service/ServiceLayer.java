package com.company.shippingservice.service;

import com.company.shippingservice.dao.InvoiceDao;
import com.company.shippingservice.dao.InvoiceItemDao;
import com.company.shippingservice.dto.Invoice;
import com.company.shippingservice.dto.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {


    //**
    // Dao objects
    // /
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;

    //**
    // ServiceLayer Constructor
    // /


    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    //**
    // Invoice Crud
    // /


    public Invoice getInvoice(int id) {return invoiceDao.getByInvoiceId(id);}

    public List<Invoice> getAllInvoices(){return invoiceDao.findAll();}

    public Invoice createInvoice(Invoice invoice) {return invoiceDao.save(invoice);}

    public void updateInvoice(Invoice invoice){invoiceDao.save(invoice);}

    public void deleteInvoice(int id){invoiceDao.deleteById(id);}

    //**
    // InvoiceItem Crud
    // /

    public InvoiceItem getInvoiceItem(int id) {return invoiceItemDao.getByInvoiceItemId(id);}

    public List<InvoiceItem> getAllInvoiceItems(){return invoiceItemDao.findAll();}

    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {return invoiceItemDao.save(invoiceItem);}

    public void updateInvoiceItem(InvoiceItem invoiceItem){invoiceItemDao.save(invoiceItem);}

    public void deleteInvoiceItem(int id){invoiceItemDao.deleteById(id);}




}
