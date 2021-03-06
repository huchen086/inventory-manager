package org.goldencloud.inventorymanager.models.dao;

import org.goldencloud.inventorymanager.models.SaleEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SaleEventDao extends CrudRepository<SaleEvent, Long> {
}
