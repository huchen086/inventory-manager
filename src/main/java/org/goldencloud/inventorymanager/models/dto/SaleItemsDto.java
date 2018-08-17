package org.goldencloud.inventorymanager.models.dto;

import org.goldencloud.inventorymanager.models.SaleItem;

import javax.validation.Valid;
import java.util.List;

public class SaleItemsDto {

    @Valid
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
