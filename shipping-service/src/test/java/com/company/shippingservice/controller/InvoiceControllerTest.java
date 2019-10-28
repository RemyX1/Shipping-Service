package com.company.shippingservice.controller;

import com.company.shippingservice.dto.Invoice;
import com.company.shippingservice.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer service;

    Invoice invoice,invoice1;



    private ObjectMapper mapper;

    {
        mapper = new ObjectMapper();
    }

    public InvoiceControllerTest() {
        this.mockMvc = mockMvc;
        this.service = service;
    }



    @Before
    public void setUp() {

        /**
         *Defining the arrange phase here avoids code repetition and saves us time.
         */

//        service.getAllInvoices().forEach(invoice -> service.deleteInvoice(invoice.getInvoiceId()));

        invoice = new Invoice(1,"12345",
                LocalDate.of(2019,10,10), new BigDecimal("35.00")
                ,new BigDecimal("0.00"),new BigDecimal("0.00"));

        invoice1 = new Invoice(1,"12345",
                LocalDate.of(2019,10,10), new BigDecimal("35.00")
                ,new BigDecimal("0.00"),new BigDecimal("0.00"));
        invoice1.setInvoiceId(1);

    }

    @Test
    public void createInvoice() throws Exception {

        String inputJson = mapper.writeValueAsString(invoice);
        String outputJson = mapper.writeValueAsString(invoice1);

        when(service.createInvoice(invoice)).thenReturn(invoice1);


        this.mockMvc.perform(post("/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getInvoice() throws Exception {

        String outputJson = mapper.writeValueAsString(invoice1);

        when(service.getInvoice(1)).thenReturn(invoice1);


        this.mockMvc.perform(get("/invoice/get/1"))
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getAllInvoices() throws Exception {

        List<Invoice> list = new ArrayList<>();
        list.add(invoice1);

        String outputJson = mapper.writeValueAsString(list);

        when(service.getAllInvoices()).thenReturn(list);


        this.mockMvc.perform(get("/invoice"))
                .andExpect(content().json(outputJson));


    }

    @Test
    public void updateInvoice() throws Exception {


        String inputJson = mapper.writeValueAsString(invoice1);

        this.mockMvc.perform(put("/invoice")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void deleteInvoice() throws Exception{

        this.mockMvc.perform(delete("/invoice/delete/1"))
                .andDo(print())
                .andExpect(content().string(""));


    }
}