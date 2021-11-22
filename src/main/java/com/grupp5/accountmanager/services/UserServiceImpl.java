package com.grupp5.services;

import com.grupp5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

@Autowired
private UserDao userDao;


    @Override
    public void getUserById(Long id) {
     userDao.findById(id);
    }
}
