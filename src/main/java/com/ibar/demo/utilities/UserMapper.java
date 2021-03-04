package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public class UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

        public static User requestDtoToUser(UserRequestDTO userRequestDTO) {
            if ( userRequestDTO == null ) {
                return null;
            }

            User.Builder user = User.build();

            user.name( userRequestDTO.getName() );
            user.email( userRequestDTO.getEmail() );
            user.age( userRequestDTO.getAge() );
            user.password( userRequestDTO.getPassword() );
            user.surname( userRequestDTO.getSurname() );
            user.card_number( userRequestDTO.getCard_number() );
            user.pin( userRequestDTO.getPin() );
            if ( userRequestDTO.getBirthday() != null ) {
                user.birthday( LocalDate.parse( userRequestDTO.getBirthday() ) );
            }
            user.gender( userRequestDTO.getGender() );

            return user.build();
        }


    public static UserResponseDTO mapUsertoUserDTO(User user, List<PhoneNumber> phoneNumbers) {
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
                .email(user.getEmail())
                .birthday(user.getBirthday().format(DateTimeFormatter.ofPattern("d/MM/yyyy")))
                .phone_numbers(collect)
                .profile_image(user.getProfile_picture_url())
                .build();


    }
}
