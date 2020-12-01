package com.ibar.demo.controllers;


import com.ibar.demo.model.User;
import com.ibar.demo.services.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Log4j2
@RestController
@RequestMapping(path = {"/users/", "/user"})

public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        log.info("getting user with " + id + " id .....");
        return new ResponseEntity(service.getUserById(id), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        log.info("adding user ......");
        return new ResponseEntity<>(service.create(user), HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        log.info("updating user ......");
        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }

}
