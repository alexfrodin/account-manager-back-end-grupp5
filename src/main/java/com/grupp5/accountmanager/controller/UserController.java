package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.models.UserM;
import com.grupp5.accountmanager.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/api/user/update/{id}")
    public ResponseEntity<UserM> updateById(@PathVariable Long id, @RequestBody UserM user){
        UserM userM = userService.updateUser(id,user);
        return new ResponseEntity<>(userM,HttpStatus.CREATED);
    }

    @PostMapping("/api/user/create")
    public ResponseEntity<UserM> createUser(@RequestBody UserM user) {
        UserM createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/getAll")
    public ResponseEntity<List<UserM>> getAllUsers() {
        List<UserM> users = null;
        try {
            users = userService.getAllUsers();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/api/user/delete/{id}")
    public ResponseEntity<UserM> deleteUserById(@PathVariable Long id){
      UserM userM = userService.deleteUser(id);
      return new ResponseEntity<>(userM,HttpStatus.OK);
    }


}
