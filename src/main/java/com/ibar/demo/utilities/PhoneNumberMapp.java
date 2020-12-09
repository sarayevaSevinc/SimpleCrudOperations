package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneNumberMapp {

    PhoneNumberMapp INSTANCE = Mappers.getMapper(PhoneNumberMapp.class);
    //  @Mapping(source ="phone", target = "phones.add(userRequestDTO.phone)")
    PhoneNumber StringToPhoneNumber(String phone);

}
