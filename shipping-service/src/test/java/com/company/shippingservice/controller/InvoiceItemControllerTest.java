package com.company.shippingservice.controller;

import com.company.shippingservice.dto.InvoiceItem;
import com.company.shippingservice.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class InvoiceItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer service;

    InvoiceItem invoiceItem,invoiceItem1;



    private ObjectMapper mapper;

    {
        mapper = new ObjectMapper();
    }

    public InvoiceItemControllerTest() {
        this.mockMvc = mockMvc;
        this.service = service;
    }



    @Before
    public void setUp() {


        /**
         *Defining the arrange phase here avoids code repetition and saves us time.
         */

        service.getAllInvoiceItems()
                .forEach(invoiceItem -> service.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        invoiceItem = new InvoiceItem(1,"name",
                "This is a description", 12, new BigDecimal("35.00"));
        invoiceItem1 = new InvoiceItem(1,"name",
                "This is a description", 12, new BigDecimal("35.00"));
        invoiceItem1.setInvoiceItemId(1);



    }

    @Test
    public void createInvoiceItem() throws Exception {

        String inputJson = mapper.writeValueAsString(invoiceItem);
        String outputJson = mapper.writeValueAsString(invoiceItem1);

        when(service.createInvoiceItem(invoiceItem)).thenReturn(invoiceItem1);


        this.mockMvc.perform(post("/invoiceItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getInvoiceItem() throws Exception {

        String outputJson = mapper.writeValueAsString(invoiceItem1);

        when(service.getInvoiceItem(1)).thenReturn(invoiceItem1);


        this.mockMvc.perform(get("/invoiceItem/get/1"))
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getAllInvoiceItems() throws Exception {

        List<InvoiceItem> list = new ArrayList<>();
        list.add(invoiceItem1);

        String outputJson = mapper.writeValueAsString(list);

        when(service.getAllInvoiceItems()).thenReturn(list);


        this.mockMvc.perform(get("/invoiceItem"))
                .andExpect(content().json(outputJson));


    }

    @Test
    public void updateInvoiceItem() throws Exception {


        String inputJson = mapper.writeValueAsString(invoiceItem1);

        this.mockMvc.perform(put("/invoiceItem")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void deleteInvoiceItem() throws Exception{

        this.mockMvc.perform(delete("/invoiceItem/delete/1"))
                .andDo(print())
                .andExpect(content().string(""));


    }
}