package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.models.Inventory;
import org.goldencloud.inventorymanager.models.SaleEvent;
import org.goldencloud.inventorymanager.models.SaleItem;
import org.goldencloud.inventorymanager.models.dao.InventoryDao;
import org.goldencloud.inventorymanager.models.dao.SaleEventDao;
import org.goldencloud.inventorymanager.models.dao.SaleItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleEventService {

    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    private SaleEventDao saleEventDao;

    @Autowired
    private SaleItemDao saleItemDao;

    @Transactional
    public void initializeNewSaleEvent(SaleEvent event) throws NullPointerException {

        List<Inventory> inventories = (List<Inventory>) inventoryDao.findAll();
        List<SaleItem> items = new ArrayList<>();

        if (inventories == null || inventories.size() == 0) {
            throw new NullPointerException("You must add inventory first.");
        }

        //initialize SaleEvent.Items with all the inventories
        for (Inventory inv : inventories) {
            SaleItem newItem = new SaleItem();
            newItem.setInventory(inv);
            newItem.setSaleEvent(event);
            saleItemDao.save(newItem);
            items.add(newItem);
        }
        event.setItems(items);

        saleEventDao.save(event);
    }

}
