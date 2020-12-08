package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.UserDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.model.User;
import javax.print.attribute.standard.RequestingUserName;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    UserDTO create(UserRequestDTO user);

    UserDTO getUserById(long id);

    UserDTO getUserByName(String name);

    UserDTO updateUser(User user);

    void deleteUserById(long id);







}
