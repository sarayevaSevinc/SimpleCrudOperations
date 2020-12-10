package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    UserResponseDTO create(UserRequestDTO user);

    UserResponseDTO getUserById(long id);

    UserResponseDTO getUserByName(String name);

    User updateUser(User user);

    void deleteUserById(long id);







}
