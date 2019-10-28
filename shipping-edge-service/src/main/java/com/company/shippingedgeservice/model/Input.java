package com.company.shippingedgeservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Input {
    private int customerId;
    private List<InvoiceItem> itemsRequested;
    private String shipToZip;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate purchaseDate;

    public Input() {
    }

    public Input(int customerId, List<InvoiceItem> itemsRequested, String shipToZip, LocalDate purchaseDate) {
        this.customerId = customerId;
        this.itemsRequested = itemsRequested;
        this.shipToZip = shipToZip;
        this.purchaseDate = purchaseDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<InvoiceItem> getItemsRequested() {
        return itemsRequested;
    }

    public void setItemsRequested(List<InvoiceItem> itemsRequested) {
        this.itemsRequested = itemsRequested;
    }

    public String getShipToZip() {
        return shipToZip;
    }

    public void setShipToZip(String shipToZip) {
        this.shipToZip = shipToZip;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Input input = (Input) o;
        return customerId == input.customerId &&
                itemsRequested.equals(input.itemsRequested) &&
                shipToZip.equals(input.shipToZip) &&
                purchaseDate.equals(input.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, itemsRequested, shipToZip, purchaseDate);
    }
}
