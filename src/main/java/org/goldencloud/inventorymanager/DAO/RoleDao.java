package org.goldencloud.inventorymanager.DAO;

import org.goldencloud.inventorymanager.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
