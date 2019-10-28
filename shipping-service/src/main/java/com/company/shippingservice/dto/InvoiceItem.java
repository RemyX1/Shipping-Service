package com.company.shippingservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice_item")
public class InvoiceItem {

    /**
     * generated value
     * Model is defined as well as relational scope
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceItemId;
    private int invoiceId;
    private String itemName;
    private String itemDescription;
    private int weight;
    private BigDecimal shipCost;

    public InvoiceItem(int invoiceId, String itemName, String itemDescription, int weight, BigDecimal shipCost) {
        this.invoiceId = invoiceId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = weight;
        this.shipCost = shipCost;
    }

    public InvoiceItem() {
    }

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public InvoiceItem setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
        return this;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public InvoiceItem setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public InvoiceItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public InvoiceItem setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public InvoiceItem setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public InvoiceItem setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoiceItemId == that.invoiceItemId &&
                invoiceId == that.invoiceId &&
                weight == that.weight &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(itemDescription, that.itemDescription) &&
                Objects.equals(shipCost, that.shipCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, itemName, itemDescription, weight, shipCost);
    }
}
