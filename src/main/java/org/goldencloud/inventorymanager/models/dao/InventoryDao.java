package org.goldencloud.inventorymanager.models.dao;

import org.goldencloud.inventorymanager.models.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InventoryDao extends CrudRepository<Inventory, String> {

}
