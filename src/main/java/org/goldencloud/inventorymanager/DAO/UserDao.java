package org.goldencloud.inventorymanager.DAO;

import org.goldencloud.inventorymanager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
