package org.goldencloud.inventorymanager.services;

import org.goldencloud.inventorymanager.DTO.UserDto;
import org.goldencloud.inventorymanager.validators.EmailExistsException;

public interface IUserService {
    void registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}
