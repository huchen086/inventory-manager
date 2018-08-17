package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.models.SaleItem;

public interface IIventoryService {

    void updateInventoryQuantity(SaleItem item) throws IllegalArgumentException;

}
