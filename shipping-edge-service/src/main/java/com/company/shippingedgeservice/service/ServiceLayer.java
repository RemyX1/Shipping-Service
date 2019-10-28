package com.company.shippingedgeservice.service;

import com.company.shippingedgeservice.model.Input;
import com.company.shippingedgeservice.model.Invoice;
import com.company.shippingedgeservice.model.InvoiceItem;
import com.company.shippingedgeservice.util.feign.InvoiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceLayer {
    private InvoiceFeignClient feign;

    @Autowired
    public ServiceLayer(InvoiceFeignClient feign) {
        this.feign = feign;
    }

    public List<Invoice> getInvoiceByCustomerId(int id){
        List<Invoice> invoiceList = feign.getAllInvoices();
        return invoiceList.stream().filter(invoice -> invoice.getCustomerId() == id).collect(Collectors.toList());
    };

    public Invoice processPayment(Input input){
        Invoice invoice = new Invoice();
        invoice.setCustomerId(input.getCustomerId());
        invoice.setShipToZip(input.getShipToZip());
        invoice.setPurchaseDate(input.getPurchaseDate());

        double totWeight = input.getItemsRequested().stream().mapToInt(item-> item.getWeight()).average().getAsDouble();
        BigDecimal shipCost;
        if(totWeight > 10 && totWeight < 17 ){
            shipCost = BigDecimal.valueOf(8.50);
        } else if (totWeight > 17 && totWeight < 25){
            shipCost = BigDecimal.valueOf(12.50);
        } else if (totWeight > 25 && totWeight < 35){
            shipCost = BigDecimal.valueOf(19.99);
        } else if (totWeight > 35){
            shipCost = BigDecimal.valueOf(50.00);
        } else {
            shipCost = BigDecimal.valueOf(0);
        }

        BigDecimal surcharge = BigDecimal.valueOf(0);
        String firstNumber = String.valueOf(invoice.getShipToZip().charAt(0));
        if(firstNumber.equals(0) || firstNumber.equals(1) || firstNumber.equals(2)){
            invoice.setSurcharge(BigDecimal.valueOf(9.99));
        } else if(firstNumber.equals(3)){
            invoice.setSurcharge(BigDecimal.valueOf(14.99));
        } else if(firstNumber.equals(4)||firstNumber.equals(5) || firstNumber.equals(6)){
            invoice.setSurcharge(BigDecimal.valueOf(19.99));
        } else {
            firstNumber.equals(24.99);
        }

        BigDecimal salesTax = (surcharge.add(shipCost)).multiply(BigDecimal.valueOf(.072));
        invoice.setSalesTax(salesTax);

        invoice.setTotalCost(shipCost.add(surcharge).add(salesTax));

        Invoice inv = feign.postInvoice(invoice);

        return inv;
    }
}
