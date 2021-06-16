package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.model.PhoneNumber;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberService {

    PhoneNumberDTO save(PhoneNumber phoneNumber);

    PhoneNumberDTO getPhoneNumberById(int id);

    List<PhoneNumberDTO> getPhoneNumberByUser(long userid);

}
