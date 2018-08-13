package org.goldencloud.inventorymanager.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Entity
public class InventoryDetail {

    //id will be generated from the foreign key
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name="property",value="inventory"))
    @Id
    @GeneratedValue(generator = "generator")
    private long id;

    @Valid
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    //InventoryDetail is the owner of the relationship.
    private Inventory inventory;

    @NotNull
    @PositiveOrZero
    private int quantity;

    @NotNull
    @Positive(message = "price must be greater than zero")
    private Double price;

    public InventoryDetail() {

    }

    public InventoryDetail(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
