package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.UserDTO;
import com.ibar.demo.model.User;

public class UserMapper {

    public  static UserDTO mapUserToUserDto(User user) {
       return UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .build();
    }
}
