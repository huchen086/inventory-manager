package org.goldencloud.inventorymanager.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Merchandise {

    @Id
    @NotBlank (message = "a SKU# must be provided")
    @NotNull
    private String sku;

    @NotBlank (message = "name must not be empty")
    @NotNull
    private String name;

    @NotBlank (message = "description must not be empty")
    @NotNull
    private String description;

    @NotNull
    @Positive (message = "price must be greater than zero")
    private Double price;

    public Merchandise() {

    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
