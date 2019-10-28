package com.company.shippingedgeservice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {
    private int invoceItemId;
    private String itemName;
    private String itemDescription;
    private int weight;
    private BigDecimal shipCost;

    public InvoiceItem() {
    }

    public InvoiceItem(String itemName, String itemDescription, int weight) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = weight;
    }

    public InvoiceItem(String itemName, String itemDescription, int weight, BigDecimal shipCost) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = weight;
        this.shipCost = shipCost;
    }

    public InvoiceItem(int invoceItemId, String itemName, String itemDescription, int weight, BigDecimal shipCost) {
        this.invoceItemId = invoceItemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = weight;
        this.shipCost = shipCost;
    }

    public int getInvoceItemId() {
        return invoceItemId;
    }

    public void setInvoceItemId(int invoceItemId) {
        this.invoceItemId = invoceItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoceItemId == that.invoceItemId &&
                weight == that.weight &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(itemDescription, that.itemDescription) &&
                Objects.equals(shipCost, that.shipCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoceItemId, itemName, itemDescription, weight, shipCost);
    }
}
