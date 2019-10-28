package com.company.shippingservice.daoTests;

import com.company.shippingservice.dao.InvoiceDao;
import com.company.shippingservice.dao.InvoiceItemDao;
import com.company.shippingservice.dto.Invoice;
import com.company.shippingservice.dto.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceItemDaoTests {

    @Autowired
    InvoiceItemDao dao;

    private InvoiceItem invoiceItem;

    @Before
    public void setUp() throws Exception {

        /**
         *Defining the arrange phase here avoids code repetition and saves us time.
         */

        dao.deleteAll();

        invoiceItem = new InvoiceItem(1,"name",
                "This is a description", 12, new BigDecimal("35.00"));
    }



    @Test
    public void saveInvoice() {

        InvoiceItem test = dao.save(invoiceItem);
        invoiceItem.setInvoiceItemId(test.getInvoiceItemId());

        assertEquals(invoiceItem,test);

    }

    @Test
    public void getInvoice() {
        invoiceItem = dao.save(invoiceItem);
        assertEquals(invoiceItem,dao.getByInvoiceItemId(invoiceItem.getInvoiceItemId()));
    }

    @Test
    public void getAllInvoices() {

        dao.save(invoiceItem);
        assertEquals(dao.findAll().size(),1);
    }

    @Test
    public void updateInvoice() {
        invoiceItem = dao.save(invoiceItem);
        invoiceItem.setItemName("new name");
        dao.save(invoiceItem);

        assertEquals(invoiceItem,dao.getByInvoiceItemId(invoiceItem.getInvoiceItemId()));

    }

    @Test
    public void deleteInvoice() {



        invoiceItem = dao.save(invoiceItem);
        dao.deleteById(invoiceItem.getInvoiceItemId());
        assertNull(dao.getByInvoiceItemId(invoiceItem.getInvoiceItemId()));

    }




}
