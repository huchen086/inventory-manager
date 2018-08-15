package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.models.SaleEvent;


public interface ISaleEventService {

    void initializeNewSaleEvent (SaleEvent event) throws NullPointerException;
}
