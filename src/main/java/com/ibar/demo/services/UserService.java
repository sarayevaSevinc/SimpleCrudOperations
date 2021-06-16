package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.model.User;
import java.util.Optional;


public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(long id);

    UserResponseDTO updateUser(UserRequestDTO user, User savedUser);

    void deleteUserById(long id);

    Optional<User> getUserByPin(String pin);

    UserResponseDTO addUserPhoneNumber(int id, PhoneNumberDTO number);


}
