package org.goldencloud.inventorymanager.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
public class InventoryDetail implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String sku;

    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Inventory inventory;

    @NotNull
    @PositiveOrZero
    private int quantity;

    public InventoryDetail() {

    }

    public InventoryDetail(Inventory inventory, int quantity) {
        setInventory(inventory);
        this.quantity = quantity;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        this.sku = inventory.getSku();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
