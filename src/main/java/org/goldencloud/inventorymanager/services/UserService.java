package org.goldencloud.inventorymanager.services;

import java.util.Arrays;
import org.goldencloud.inventorymanager.DAO.RoleDao;
import org.goldencloud.inventorymanager.DAO.UserDao;
import org.goldencloud.inventorymanager.DTO.UserDto;
import org.goldencloud.inventorymanager.models.Role;
import org.goldencloud.inventorymanager.validators.EmailExistsException;
import org.goldencloud.inventorymanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Transactional
    public void registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address: " + accountDto.getEmail());
        }
        User user = new User();
        Role userRole = roleDao.findByName("ROLE_USER");
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setEnabled(true);
        userDao.save(user);
    }
    private boolean emailExist(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
