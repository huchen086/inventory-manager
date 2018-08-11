package org.goldencloud.inventorymanager.models.dao;

import org.goldencloud.inventorymanager.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
