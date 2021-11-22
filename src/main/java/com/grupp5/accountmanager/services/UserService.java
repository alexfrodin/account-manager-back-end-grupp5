package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.models.UserM;

public interface UserService {

    public UserM getUserById(Long id);

    public UserM createUser(String userName, String userEmail, String userImageUrl, String password);

}
