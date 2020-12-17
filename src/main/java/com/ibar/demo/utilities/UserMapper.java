package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.User;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestDtoToUser(UserRequestDTO userRequestDTO);

    default UserResponseDTO mapUsertoUserDTO(User user, List<PhoneNumber> phoneNumbers) {
        if (user == null) {
            return null;
        }
        List<PhoneNumberDTO> collect = new ArrayList<>();
        if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
            collect = phoneNumbers.stream().map(phone -> PhoneNumberDTO.builder()
                    .phone(phone.getPhone())
                    .build()).collect(Collectors.toList());
        }
        return UserResponseDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .birthday(user.getBirthday().format(DateTimeFormatter.ofPattern("d/MM/yyyy")))
                .phone_numbers(collect)
                .profile_image(user.getProfile_picture_url())
                .build();


    }
}
