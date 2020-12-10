package com.ibar.demo.services.impl;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.exceptions.PhoneNumberWithIdNotFound;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.StaticVariable;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhoneNumberRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.PhoneNumberService;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.PhoneNumberMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    PhoneNumberRepository phoneNumberRepository;
    UserRepository userRepository;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository, UserRepository userRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.userRepository = userRepository;
    }


    @Override
    public PhoneNumberDTO save(PhoneNumber phoneNumber) {
        return PhoneNumberMapper.
                mapPhoneNumberToPhoneNumberDto(phoneNumberRepository.save(phoneNumber));
    }
    public PhoneNumber createPhoneNumber(String phoneNumber, User save){
      return   phoneNumberRepository.save(PhoneNumber.builder()
                .user(save)
                .phone(phoneNumber)
                .build());
    }

    @Override
    public PhoneNumberDTO getPhoneNumberById(int id) {
        Optional<PhoneNumber> byId = phoneNumberRepository.findById(id);
        if(byId.isPresent()) {
            return PhoneNumberMapper.mapPhoneNumberToPhoneNumberDto(byId.get());
        }
        throw new PhoneNumberWithIdNotFound(ErrorMapper.getPhoneNumberNotFoundWithIDError());
    }

    @Override
    public List<PhoneNumberDTO> getPhoneNumberByUser(long userid) {
       List <PhoneNumber> byUserId = phoneNumberRepository.findByUserId(userid);
        if(!byUserId.isEmpty()) {
            return PhoneNumberMapper.mapPhoneNumbersToPhoneNumberDto(byUserId);
        }
        throw new PhoneNumberWithIdNotFound(ErrorMapper.getPhoneNumberNotFoundWithIDError());
    }

//    @Bean
//    void addPhoneNumber(){
//      phoneNumberRepository.save(PhoneNumber.builder()
//      .user(userRepository.getUserById(1).get())
//      .phone("+994774081550")
//      .build());
//    }
}
