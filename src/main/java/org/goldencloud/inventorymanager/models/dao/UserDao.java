package org.goldencloud.inventorymanager.models.dao;

import org.goldencloud.inventorymanager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
