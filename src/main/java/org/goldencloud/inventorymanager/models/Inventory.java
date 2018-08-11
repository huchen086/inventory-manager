package org.goldencloud.inventorymanager.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Inventory {
    @Id
    @Column(insertable = false, updatable = false)
    private String sku;

    @OneToOne(optional = false)
    @JoinColumn(name = "sku", nullable = false)
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
