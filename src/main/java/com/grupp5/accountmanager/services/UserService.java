package com.grupp5.accountmanager.services;


import com.grupp5.accountmanager.models.UserM;

import java.util.List;

public interface UserService {

    List<UserM> getAllUsers();
    UserM getUserById(Long id);

    UserM createUser(UserM userM);
    UserM updateUser(Long id,UserM userM);
    UserM deleteUser(Long id);





}
