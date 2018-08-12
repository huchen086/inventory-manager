package org.goldencloud.inventorymanager.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
public class Inventory implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String sku;

    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Merchandise merchandise;

    @NotNull
    @PositiveOrZero
    private int quantity;

    public Inventory() {

    }

    public Inventory(Merchandise merchandise, int quantity) {
        setMerchandise(merchandise);
        this.quantity = quantity;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
        this.sku = merchandise.getSku();
    }

    public Merchandise getMerchandise() {
        return merchandise;
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
