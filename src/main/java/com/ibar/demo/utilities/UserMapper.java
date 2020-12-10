package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
   //@Mapping(source ="phone", target = "PhoneNumberMapp.StringToPhoneNumber()")
   // User requestDtoToUser(UserRequestDTO userRequestDTO);
    UserResponseDTO userToUserDTO(User user);

     default User requestDtoToUser(UserRequestDTO userRequestDTO){
         if(userRequestDTO==null)
             return null;
         return User.build()
                .name(userRequestDTO.getName())
                .surname(userRequestDTO.getSurname())
                .age(userRequestDTO.getAge())
                .birthday(LocalDate.parse(userRequestDTO.getBirthday()))
                .cardNumber(userRequestDTO.getCardNumber())
                .pin(userRequestDTO.getPin())
                .gender(userRequestDTO.getGender())
                 .profilePictureUrl("There is no profil picture for this user.")
                .build();
    }

    default List<PhoneNumber> stringToPhoneNumber(String s){
         if(s==null) return null;
         List<PhoneNumber> phones =  new ArrayList<>();
         phones.add(PhoneNumber.builder()
                 .phone(s)
                 .build());
       return phones;
    }
    default UserResponseDTO mapUsertoUserDTO(User user, List<PhoneNumber> phoneNumbers){
         if(user == null)
             return null;
        List<PhoneNumberDTO> collect = new ArrayList<>();
        if(phoneNumbers != null && !phoneNumbers.isEmpty()) {
            collect = phoneNumbers.stream().map(phone -> PhoneNumberDTO.builder()
                    .phone(phone.getPhone())
                    .build()).collect(Collectors.toList());
        }
       return UserResponseDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .birthday(user.getBirthday())
                .phoneNumbers(collect)
               .profileImage(user.getProfilePictureUrl())
                .build();


    }

    default UserResponseDTO mapUserWithProfilePicturetoUserDTO(User user, List<PhoneNumber> phoneNumbers, String url){
        if(user == null)
            return null;
        List<PhoneNumberDTO> collect = new ArrayList<>();
        if(phoneNumbers != null && !phoneNumbers.isEmpty()) {
            collect = phoneNumbers.stream().map(phone -> PhoneNumberDTO.builder()
                    .phone(phone.getPhone())
                    .build()).collect(Collectors.toList());
        }
        return UserResponseDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .birthday(user.getBirthday())
                .phoneNumbers(collect)
                .profileImage(url)
                .build();


    }

    PhoneNumberDTO phoneNumbertoPhoneNumberDto(PhoneNumber phoneNumber);
    //UserDTO fromUserToUserDTO(User user, List<PhoneNumber> phones);
}
