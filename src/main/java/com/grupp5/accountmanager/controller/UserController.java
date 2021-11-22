package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.models.UserM;
import com.grupp5.accountmanager.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Value("${app.base.url}")
    private String base_url;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserM> findById(@PathVariable Long id) {
        UserM userM = userService.getUserById(id);
        return new ResponseEntity<>(userM, HttpStatus.OK);
    }

    @PostMapping("/api/user/create")
    public ResponseEntity<UserM> createUser(@RequestBody UserM user) {
        System.out.println(base_url);
        UserM createdUser = userService.createUser(user.getUserName(), user.getUserEmail(), user.getUserImageUrl(), user.getHashedPassword()); // TODO: Fix separate "password" and "hashedPassword"

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


}
