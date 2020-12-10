package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.model.PhoneNumber;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneNumberMapp {

    PhoneNumberMapp INSTANCE = Mappers.getMapper(PhoneNumberMapp.class);

    PhoneNumberDTO phoneNumberToPhoneNumberDTO(PhoneNumber phoneNumber);

    PhoneNumber phoneNumberDTOToPhoneNumber(PhoneNumberDTO phoneNumberDTO);

    default List<PhoneNumberDTO> mapPhoneNumbersToPhoneNumberDto(List<PhoneNumber> phoneNumbers){
        List<PhoneNumberDTO> phoneNumberDTOS = new ArrayList<>();

        for (PhoneNumber number : phoneNumbers) {
            phoneNumberDTOS.add(phoneNumberToPhoneNumberDTO(number));
        }
        return phoneNumberDTOS;
    }

}
