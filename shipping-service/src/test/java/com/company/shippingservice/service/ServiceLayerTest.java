package com.company.shippingservice.service;

import com.company.shippingservice.dao.InvoiceDao;
import com.company.shippingservice.dao.InvoiceItemDao;
import com.company.shippingservice.dto.Invoice;
import com.company.shippingservice.dto.InvoiceItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ServiceLayerTest {


    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    ServiceLayer service;

    Invoice invoice;
    InvoiceItem invoiceItem;


    @Before
    public void setUp() throws Exception {

        /**
         *Defining the arrange phase here avoids code repetition and saves us time.
         */

        createInvoiceMock();
        createInvoiceItemMock();


        service = new ServiceLayer(invoiceDao,invoiceItemDao);


        invoice = new Invoice(1,"12345",
                LocalDate.of(2019,10,10), new BigDecimal("35.00")
                ,new BigDecimal("0.00"),new BigDecimal("0.00"));

        invoiceItem = new InvoiceItem(1,"name",
                "This is a description", 12, new BigDecimal("35.00"));


    }

    @Test
    public void getInvoice() {

        invoice = service.createInvoice(invoice);
        assertEquals(invoice,service.getInvoice(1));
    }

    @Test
    public void getAllInvoices() {
        service.createInvoice(invoice);
        assertEquals(service.getAllInvoices().size(),1);
    }

    @Test
    public void createInvoice() {

        invoice = service.createInvoice(invoice);
        assertEquals(invoice,service.getInvoice(1));
    }

    @Test
    public void updateInvoice() {

        invoice = service.createInvoice(invoice);
        invoice.setCustomerId(2);
        service.updateInvoice(invoice);
        assertEquals(invoice,service.getInvoice(invoice.getInvoiceId()));
    }

    @Test
    public void deleteInvoice() {

        invoice = service.createInvoice(invoice);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        //act
        service.deleteInvoice(invoice.getInvoiceId());
        //assert that service layer passed the id value as expected
        verify(invoiceDao, times(1)).deleteById(intCaptor.capture());

    }

    @Test
    public void getInvoiceItem() {
        assertEquals(service.createInvoiceItem(invoiceItem),service.getInvoiceItem(1));
    }

    @Test
    public void getAllInvoiceItems() {
        service.createInvoiceItem(invoiceItem);
        assertEquals(service.getAllInvoiceItems().size(),1);
    }

    @Test
    public void createInvoiceItem() {
        assertEquals(service.createInvoiceItem(invoiceItem),service.getInvoiceItem(1));
    }

    @Test
    public void updateInvoiceItem() {
        invoiceItem = service.createInvoiceItem(invoiceItem);
        invoiceItem.setItemName("new name");
        service.updateInvoiceItem(invoiceItem);
        assertEquals(invoiceItem,service.getInvoiceItem(invoiceItem.getInvoiceItemId()));
    }

    @Test
    public void deleteInvoiceItem() {
        invoiceItem = service.createInvoiceItem(invoiceItem);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        //act
        service.deleteInvoiceItem(invoiceItem.getInvoiceItemId());
        //assert that service layer passed the id value as expected
        verify(invoiceItemDao, times(1)).deleteById(intCaptor.capture());


    }


    public void createInvoiceMock() {

        invoiceDao = mock(InvoiceDao.class);

        Invoice invoice = new Invoice(1,"12345",
                LocalDate.of(2019,10,10), new BigDecimal("35.00")
                ,new BigDecimal("0.00"),new BigDecimal("0.00"));


        Invoice invoice1 = new Invoice(1,"12345",
                LocalDate.of(2019,10,10), new BigDecimal("35.00")
                ,new BigDecimal("0.00"),new BigDecimal("0.00"));
        invoice1.setInvoiceId(1);

        List<Invoice> list = new ArrayList<>();
        list.add(invoice1);

        doReturn(invoice1).when(invoiceDao).save(invoice);
        doReturn(invoice1).when(invoiceDao).getByInvoiceId(1);
        doReturn(list).when(invoiceDao).findAll();


    }

    public void createInvoiceItemMock() {

        invoiceItemDao = mock(InvoiceItemDao.class);

        InvoiceItem invoiceItem = new InvoiceItem(1,"name",
                "This is a description", 12, new BigDecimal("35.00"));

        InvoiceItem invoiceItem1 = new InvoiceItem(1,"name",
                "This is a description", 12, new BigDecimal("35.00"));
        invoiceItem1.setInvoiceItemId(1);


        List<InvoiceItem> list = new ArrayList<>();
        list.add(invoiceItem1);


        doReturn(invoiceItem1).when(invoiceItemDao).save(invoiceItem);
        doReturn(invoiceItem1).when(invoiceItemDao).getByInvoiceItemId(1);
        doReturn(list).when(invoiceItemDao).findAll();



    }






}