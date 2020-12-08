package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.model.PhoneNumber;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberMapper {

    public static PhoneNumberDTO mapPhoneNumberToPhoneNumberDto(PhoneNumber phoneNumber){
        return PhoneNumberDTO.builder()
                .phone(phoneNumber.getPhone())
                .build();
    }
    public static List<PhoneNumberDTO> mapPhoneNumbersToPhoneNumberDto(List<PhoneNumber> phoneNumbers){
        List<PhoneNumberDTO> phoneNumberDTOS = new ArrayList<>();

        for (PhoneNumber number : phoneNumbers) {
            phoneNumberDTOS.add(PhoneNumberDTO.builder()
                    .phone(number.getPhone())
                    .build());
        }
        return phoneNumberDTOS;
    }
}
