package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.models.UserM;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



public interface UserService {


public UserM getUserById(Long id);
}
