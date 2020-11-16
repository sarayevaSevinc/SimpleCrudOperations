package com.ibar.demo.controllers;


import com.ibar.demo.model.User;
import com.ibar.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = {"/users/", "/user"})

public class UserController {
    @Autowired
    UserService service;



//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(@PathVariable int id) {
//        return  new ResponseEntity(service.getUserbyId(id), HttpStatus.OK);
//    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }

}
