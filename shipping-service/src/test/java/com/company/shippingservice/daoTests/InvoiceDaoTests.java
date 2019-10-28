package com.company.shippingservice.daoTests;

import com.company.shippingservice.dao.InvoiceDao;
import com.company.shippingservice.dto.Invoice;
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
public class InvoiceDaoTests {

    @Autowired
    InvoiceDao dao;

    private Invoice invoice;

    @Before
    public void setUp() throws Exception {

        /**
         *Defining the arrange phase here avoids code repetition and saves us time.
         */

        dao.deleteAll();

        invoice = new Invoice(1,"12345",
                LocalDate.of(2019,10,10), new BigDecimal("35.00")
        ,new BigDecimal("0.00"),new BigDecimal("0.00"));
    }



    @Test
    public void saveInvoice() {

    Invoice test = dao.save(invoice);
    invoice.setInvoiceId(test.getInvoiceId());

    assertEquals(invoice,test);

    }

    @Test
    public void getInvoice() {
        invoice = dao.save(invoice);
        assertEquals(invoice,dao.getByInvoiceId(invoice.getInvoiceId()));
    }

    @Test
    public void getAllInvoices() {

        dao.save(invoice);
        assertEquals(dao.findAll().size(),1);
    }

    @Test
    public void updateInvoice() {
        invoice = dao.save(invoice);
        invoice.setCustomerId(2);
        dao.save(invoice);

        assertEquals(invoice,dao.getByInvoiceId(invoice.getInvoiceId()));

    }

    @Test
    public void deleteInvoice() {



        invoice = dao.save(invoice);
        dao.deleteById(invoice.getInvoiceId());
        assertNull(dao.getByInvoiceId(invoice.getInvoiceId()));

    }





}
