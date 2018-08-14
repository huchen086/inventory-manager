package org.goldencloud.inventorymanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Positive
    private int quantity;

    @NotNull
    @ManyToOne
    private Inventory inventory;

    @NotNull
    @ManyToOne
    private SaleEvent saleEvent;

    public SaleItem() {
    }

    public SaleItem(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public SaleEvent getSaleEvent() {
        return saleEvent;
    }

    public void setSaleEvent(SaleEvent saleEvent) {
        this.saleEvent = saleEvent;
    }
}
