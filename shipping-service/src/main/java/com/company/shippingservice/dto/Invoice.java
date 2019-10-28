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
@Table(name = "invoice")
public class Invoice {

    /**
     * Model and scope defined
     *
     */


    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int invoiceId;
        private int customerId;
        private String shipToZip;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        private LocalDate purchaseDate;
        private BigDecimal totalCost;
        private BigDecimal salesTax;
        private BigDecimal surcharge;

        public Invoice(int customerId, String shipToZip, LocalDate purchaseDate,
                           BigDecimal totalCost, BigDecimal salesTax, BigDecimal surcharge) {
            this.customerId = customerId;
            this.shipToZip = shipToZip;
            this.purchaseDate = purchaseDate;
            this.totalCost = totalCost;
            this.salesTax = salesTax;
            this.surcharge = surcharge;
        }

        public Invoice() {
        }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Invoice setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Invoice setCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getShipToZip() {
        return shipToZip;
    }

    public Invoice setShipToZip(String shipToZip) {
        this.shipToZip = shipToZip;
        return this;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Invoice setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public Invoice setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public Invoice setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
        return this;
    }

    public BigDecimal getSurcharge() {
        return surcharge;
    }

    public Invoice setSurcharge(BigDecimal surcharge) {
        this.surcharge = surcharge;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceId == invoice.invoiceId &&
                customerId == invoice.customerId &&
                Objects.equals(shipToZip, invoice.shipToZip) &&
                Objects.equals(purchaseDate, invoice.purchaseDate) &&
                Objects.equals(totalCost, invoice.totalCost) &&
                Objects.equals(salesTax, invoice.salesTax) &&
                Objects.equals(surcharge, invoice.surcharge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, shipToZip, purchaseDate, totalCost, salesTax, surcharge);
    }
}


