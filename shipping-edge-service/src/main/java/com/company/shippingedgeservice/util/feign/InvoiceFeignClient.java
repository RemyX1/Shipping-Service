package com.company.shippingedgeservice.util.feign;

import com.company.shippingedgeservice.model.Invoice;
import com.company.shippingedgeservice.model.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "shipping-service")
public interface InvoiceFeignClient {
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public Invoice postInvoice(@RequestBody @Valid Invoice invoice);

    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)
    public InvoiceItem postInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public Invoice getInvoice(@PathVariable("id") int id);

    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.GET)
    public InvoiceItem getInvoiceItem(@PathVariable("id") int id);

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    public Invoice putInvoice(@RequestBody @Valid Invoice invoice);

    @RequestMapping(value = "/invoiceItem", method = RequestMethod.PUT)
    public InvoiceItem putInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public Invoice deleteInvoice(@PathVariable("id") int id);

    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.DELETE)
    public InvoiceItem deleteInvoiceItem(@PathVariable("id") int id);

    @RequestMapping(value = "/invoice/", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices();

    @RequestMapping(value = "/invoiceItem/", method = RequestMethod.GET)
    public List<InvoiceItem> getAllInvoiceItems();
}
