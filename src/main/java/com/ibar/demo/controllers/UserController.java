package com.ibar.demo.controllers;


import com.ibar.demo.model.StaticVariable;
import com.ibar.demo.model.User;
import com.ibar.demo.services.impl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Log4j2
@RestController
@Validated
@RequestMapping(path = {"/users/", "/user", "/api/"})
@Api(value = "Simple Crud Api")
public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @ApiOperation(value = "user", notes = "Get user account by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        log.info("getting user with " + id + " id .....");
        return new ResponseEntity(service.getUserById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "user", notes = "Adding user to mongo db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        log.info("adding user ......");

        return new ResponseEntity<>(service.create(user), HttpStatus.OK);
    }

    @ApiOperation(value = "user", notes = "Adding profil picture to db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/addProfilPhoto")
    public ResponseEntity<User> addProfilPhoto(@RequestParam(name = "id") int id,
                                               @RequestParam(name = "link") String link) {
        service.setProfilPicture(id, link);

        log.info("user " + service.getUserById(id).toString());

        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "redirecting the profil picture", notes = "Get user profil picture by user id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = RedirectView.class)})
    @GetMapping("/getProfilPhoto")
    public RedirectView getProfilPhoto(@RequestParam(name = "id") int id) {

        log.info("Redirecting the profil picture...");

        return new RedirectView(service.getProfilPicture(id));
    }

    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        log.info("updating user ......");

        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }

}
