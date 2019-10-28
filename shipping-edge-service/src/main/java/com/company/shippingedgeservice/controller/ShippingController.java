package com.company.shippingedgeservice.controller;

import com.company.shippingedgeservice.model.Input;
import com.company.shippingedgeservice.model.Invoice;
import com.company.shippingedgeservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/invoice")
public class ShippingController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice postInvoice(@RequestBody @Valid Input input){
        return serviceLayer.processPayment(input);
    };

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerId(@PathVariable int id){
        return serviceLayer.getInvoiceByCustomerId(id);
    }
}
