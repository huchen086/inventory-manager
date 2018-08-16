package org.goldencloud.inventorymanager.models.dto;

import org.goldencloud.inventorymanager.models.SaleItem;

import java.util.List;

public class SaleItemsDto {

    private List<SaleItem> items;

    public SaleItemsDto() {
    }

    public SaleItemsDto(List<SaleItem> items) {
        this.items = items;
    }

    public void addItem(SaleItem item) {
        this.items.add(item);
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }
}
