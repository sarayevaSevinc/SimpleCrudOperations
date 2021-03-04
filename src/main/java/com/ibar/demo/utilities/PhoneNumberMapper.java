package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.model.PhoneNumber;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public class PhoneNumberMapper {

    PhoneNumberMapper INSTANCE = Mappers.getMapper(PhoneNumberMapper.class);

    public static PhoneNumberDTO phoneNumberToPhoneNumberDTO(PhoneNumber phoneNumber) {
        if ( phoneNumber == null ) {
            return null;
        }

        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();

        phoneNumberDTO.setPhone( phoneNumber.getPhone() );

        return phoneNumberDTO;
    }



    public static List<PhoneNumberDTO> mapPhoneNumbersToPhoneNumberDto(List<PhoneNumber> phoneNumbers){
        List<PhoneNumberDTO> phoneNumberDTOS = new ArrayList<>();

        for (PhoneNumber number : phoneNumbers) {
            phoneNumberDTOS.add(phoneNumberToPhoneNumberDTO(number));
        }
        return phoneNumberDTOS;
    }

}
