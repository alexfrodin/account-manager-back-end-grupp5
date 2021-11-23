package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.models.UserM;

public interface UserService {

    UserM getUserById(Long id);

    UserM createUser(UserM user);

}
