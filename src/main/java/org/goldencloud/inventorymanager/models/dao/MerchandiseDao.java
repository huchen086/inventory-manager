package org.goldencloud.inventorymanager.models.dao;

import org.goldencloud.inventorymanager.models.Merchandise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MerchandiseDao extends CrudRepository<Merchandise, String> {

}
