package org.goldencloud.inventorymanager.models.dao;

import org.goldencloud.inventorymanager.models.InventoryDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InventoryDetailDao extends CrudRepository<InventoryDetail, Long> {
}
