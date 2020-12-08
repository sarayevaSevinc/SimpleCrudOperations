package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.UserDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMap {
    UserMap INSTANCE = Mappers.getMapper(UserMap.class);
    User requestDtoToUser(UserRequestDTO userRequestDTO);
    UserDTO userToUserDTO(User user);

    //@Mapping(source = )
    UserDTO fromUserToUserDTO(User user, List<PhoneNumber> phones);
}
