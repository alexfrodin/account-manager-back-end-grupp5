package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.common.ApiString;
import com.grupp5.accountmanager.models.UserM;
import com.grupp5.accountmanager.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Value("${App.Url}")
    private String base_url;

    @Autowired
    UserServiceImpl userService;


    @GetMapping("localhost:8080/user/{id}")
    public ResponseEntity<UserM> findById(@PathVariable Long id) {
        UserM userM = userService.getUserById(id);
        return new ResponseEntity<>(userM, HttpStatus.OK);
    }


}
