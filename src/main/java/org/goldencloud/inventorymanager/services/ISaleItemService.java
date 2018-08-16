package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.models.SaleItem;

import java.util.List;

public interface ISaleItemService {

    void updateQuantity(List<SaleItem> newItems) throws NullPointerException;

}
