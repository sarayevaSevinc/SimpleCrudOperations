package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserDTO;
import com.ibar.demo.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public  static UserDTO mapUserToUserDto(User user) {
       return UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
               .phoneNumbers(new ArrayList<>())
               .birthday(user.getBirthday())
                .build();
    }
    public  static UserDTO addPhoneNumberToUserDto(UserDTO userDTO, List<PhoneNumberDTO> phoneNumberDTO) {
        userDTO.setPhoneNumbers(phoneNumberDTO);
        return userDTO;
    }
}
