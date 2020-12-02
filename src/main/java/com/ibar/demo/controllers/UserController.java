package com.ibar.demo.controllers;


import com.ibar.demo.model.User;
import com.ibar.demo.services.impl.UserServiceImpl;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Log4j2
@RestController
@Validated
@RequestMapping(path = {"/users/", "/user"})

public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
       log.info("getting user with " + id + " id .....");
        return new ResponseEntity(service.getUserById(id), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        log.info("adding user ......");

        return new ResponseEntity<>(service.create(user), HttpStatus.OK);
    }


    @PostMapping("/addProfilPhoto")
    public ResponseEntity<User> addProfilPhoto(@RequestParam(name = "id") int id, @RequestParam(name = "link") String link) {
        log.info(link);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @GetMapping("/getProfilPhoto")
    public RedirectView getProfilPhoto(@RequestParam(name = "id") int id) {
        return new RedirectView(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fhuman%2520face%2F&psig=AOvVaw2xtns8NrC-Y_OtEkxNjNn_&ust=1606989469953000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCODTireEr-0CFQAAAAAdAAAAABAD");
    }
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
       // log.info("updating user ......");
        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }

}
