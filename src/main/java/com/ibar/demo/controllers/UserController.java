package com.ibar.demo.controllers;


import com.ibar.demo.model.User;
import com.ibar.demo.services.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = {"/users/", "/user"})

public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return  new ResponseEntity(service.getUserById(id), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }

}
