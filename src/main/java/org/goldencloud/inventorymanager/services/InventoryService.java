package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.models.InventoryDetail;
import org.goldencloud.inventorymanager.models.SaleItem;
import org.goldencloud.inventorymanager.models.dao.InventoryDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    private InventoryDetailDao inventoryDetailDao;

    @Transactional
    public void updateInventoryQuantity(SaleItem item, Integer diff) throws IllegalArgumentException {
        //get the quantity in stock
        long id = item.getInventory().getId();
        InventoryDetail inventoryDetail = inventoryDetailDao.findById(id).orElseThrow(NullPointerException::new);
        int inStock = inventoryDetail.getQuantity();

        //check if remain would be negative
        int remain = inStock + diff;
        if (remain < 0) {
            throw new IllegalArgumentException("Invalid sales quantity: it must not be greater than inventory at stock.");
        }

        //update inventory detail
        inventoryDetail.setQuantity(remain);
        inventoryDetailDao.save(inventoryDetail);
    }
}
