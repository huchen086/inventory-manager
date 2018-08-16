package org.goldencloud.inventorymanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "quantity must not be null")
    @PositiveOrZero(message = "quantity must not be negative")
    private int quantity;

    @NotNull(message = "inventory must not be null")
    @ManyToOne
    private Inventory inventory;

    @NotNull(message = "sale event must not be null")
    @ManyToOne
    private SaleEvent saleEvent;

    public SaleItem() {
        quantity = 0;
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
