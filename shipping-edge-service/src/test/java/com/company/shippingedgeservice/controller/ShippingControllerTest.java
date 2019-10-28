package com.company.shippingedgeservice.controller;

import com.company.shippingedgeservice.model.Input;
import com.company.shippingedgeservice.model.Invoice;
import com.company.shippingedgeservice.model.InvoiceItem;
import com.company.shippingedgeservice.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ShippingController.class)
public class ShippingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    private <T> String writeToJson(T obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }

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

    }

    @Test
    public void postInvoice() throws Exception {
        when(serviceLayer.processPayment(input)).thenReturn(inv2);
        mockMvc.perform(post("/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(writeToJson(input)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(writeToJson(inv2)));
    }

    @Test
    public void getInvoiceByCustomerId() throws Exception {
        when(serviceLayer.getInvoiceByCustomerId(1)).thenReturn(invList);
        mockMvc.perform(get("/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(writeToJson(invList)));
    }
}