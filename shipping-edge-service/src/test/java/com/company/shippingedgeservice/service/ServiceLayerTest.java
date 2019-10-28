package com.company.shippingedgeservice.service;

import com.company.shippingedgeservice.model.Input;
import com.company.shippingedgeservice.model.Invoice;
import com.company.shippingedgeservice.model.InvoiceItem;
import com.company.shippingedgeservice.util.feign.InvoiceFeignClient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ServiceLayerTest {

    private InvoiceFeignClient feign;
    private ServiceLayer serviceLayer;

    Invoice inv1, inv2;

    List<Invoice> invList = new ArrayList<>();

    Input input;
    List<InvoiceItem> itemList = new ArrayList<>();
    InvoiceItem invI1, invI2;


    @Before
    public void setUp() throws Exception {
        inv1 = new Invoice(
                1, 1,"30004", LocalDate.of(2012,12,12), BigDecimal.valueOf(100.00), BigDecimal.valueOf(10.00), BigDecimal.valueOf(12.50)
        );
        inv2 = new Invoice(
                1, 1,"30004", LocalDate.of(2011,11,11), BigDecimal.valueOf(25.18), BigDecimal.valueOf(1.69), BigDecimal.valueOf(8.50)
        );
        invList.add(inv1);
        invList.add(inv2);

        invI1 = new InvoiceItem(
                "Soap", "cleans", 5
        );

        invI1 = new InvoiceItem(
                "Towels", "drys", 6
        );

        itemList.add(invI1);
        itemList.add(invI2);

        input = new Input(
                1, itemList,"30004", LocalDate.of(2011,11,11)
        );


        setUpFeignClientMock();

        serviceLayer = new ServiceLayer(feign);
    }

    private void setUpFeignClientMock(){
        feign = mock(InvoiceFeignClient.class);
    }

    @Test
    public void getInvoiceByCustomerId() {
        when(feign.getAllInvoices()).thenReturn(invList);
        List<Invoice> actualList = serviceLayer.getInvoiceByCustomerId(1);
        assertEquals(invList, actualList);
    }

    @Test
    public void processPayment() {
        Invoice actual = serviceLayer.processPayment(input);
        assertEquals(inv2,actual);
    }
}