package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.model.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    UserResponseDTO create(UserRequestDTO user);

    UserResponseDTO getUserByIdFromDb(long id);

    UserResponseDTO getUserByName(String name);

    UserResponseDTO updateUser(UserRequestDTO user, User savedUser);

    void deleteUserById(long id);

    Optional<User> getUserByPin(String pin);







}
