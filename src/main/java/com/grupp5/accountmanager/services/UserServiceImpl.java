package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.dao.UserDao;
import com.grupp5.accountmanager.models.UserM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

@Autowired
private UserDao userDao;

    @Override
    public UserM getUserById(Long id) {
     return userDao.findById(id).orElseThrow();
    }
}
