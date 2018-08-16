package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.models.SaleItem;
import org.goldencloud.inventorymanager.models.dao.SaleItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemDao saleItemDao;

    @Transactional
    public void updateQuantity(List<SaleItem> newItems) throws NullPointerException {

        if (newItems == null || newItems.size() == 0) {
            throw new NullPointerException("Sale items not found or invalid");
        }

        long id;
        int newQuantity;

        //iterate through the list, get each item's quantity, and update the entity in database.
        for (SaleItem newItem : newItems) {
            id = newItem.getId();
            newQuantity = newItem.getQuantity();
            SaleItem theItem = saleItemDao.findById(id).orElse(null);
            if (theItem == null) {
                throw new NullPointerException("Sale items not found or invalid");
            }
            theItem.setQuantity(newQuantity);
            saleItemDao.save(theItem);
        }
    }
}
