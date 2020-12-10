package com.ibar.demo.services.impl;

import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.Photo;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhoneNumberRepository;
import com.ibar.demo.repositories.PhotoRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.UserService;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PhoneNumberServiceImpl phoneService;
    private PhoneNumberRepository phoneRepository;
    private PhotoRepository photoRepository;



    public UserServiceImpl(UserRepository userRepository, PhoneNumberServiceImpl phoneService,
                           PhoneNumberRepository repository, PhotoRepository photoRepository) {

        this.userRepository = userRepository;
        this.phoneService = phoneService;
        this.phoneRepository = repository;
        this.phoneRepository = phoneRepository;


    }


    @Override
    public UserResponseDTO create(UserRequestDTO user) {

        User save = userRepository.save(UserMapper.INSTANCE.requestDtoToUser(user));


        phoneRepository.save(PhoneNumber.builder()
                .user(save)
                .phone(user.getPhone())
                .build());

        List<PhoneNumber> byUserId = phoneRepository.findByUserId(save.getId());

        return UserMapper.INSTANCE.mapUsertoUserDTO(save, byUserId);

    }


    @Override
    public UserResponseDTO getUserById(long id) {
        log.info("Searching user with " + id + " id....");
        Optional<User> userById = userRepository.getUserById(id).filter(x -> x.getStatus() != (Status.DELETED));
        if (userById.isPresent()) {
            List<PhoneNumber> phonesByUserId = phoneRepository.findByUserId(userById.get().getId());

            return UserMapper.INSTANCE.mapUsertoUserDTO(userById.get(), phonesByUserId);
        }
        throw new AccountNotFoundException(ErrorMapper.getUserNotFoundByIdError());
    }

    @Override
    public UserResponseDTO getUserByName(String name) {
        log.info("Searching user with " + name + " name....");

        Optional<User> userByName = userRepository.getUserByName(name).filter(x -> x.getStatus() != (Status.DELETED));
        if (userByName.isPresent()) {
            List<PhoneNumber> phonesByUserId = phoneRepository.findByUserId(userByName.get().getId());

            return UserMapper.INSTANCE.mapUsertoUserDTO(userByName.get(), phonesByUserId);
        }
        throw new AccountNotFoundException(ErrorMapper.getUserNotFoundByNameError());
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        log.info("Updating user.. ");

        User user  = UserMapper.INSTANCE.requestDtoToUser(userRequestDTO);
        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        log.info("User has been updated.. " + user.getId());

        User save = userRepository.save(user);

        phoneRepository.save(PhoneNumber.builder()
                .user(save)
                .phone(userRequestDTO.getPhone())
                .build());

        List<PhoneNumber> byUserId = phoneRepository.findByUserId(save.getId());

        return UserMapper.INSTANCE.mapUsertoUserDTO(save, byUserId);
    }

    public UserResponseDTO addUserPhoneNumber(int id, String number) {
        Optional<User> userById = userRepository.getUserById(id);

        if (userById.isPresent()) {

            PhoneNumber build = PhoneNumber.builder()
                    .phone(number)
                    .user(userById.get())
                    .build();

            log.info(build.getPhone());
            phoneService.save(build);

            List<PhoneNumber> phones = phoneRepository.findByUserId(userById.get().getId());
            UserResponseDTO userResponseDTO = UserMapper.INSTANCE.mapUsertoUserDTO(userById.get(), phones);

            return userResponseDTO;

        }
        throw new AccountNotFoundException(ErrorMapper.getUserNotFoundByIdError());
    }

    @Override
    public void deleteUserById(long id) {
        log.info("User is deleting....");

        Optional<User> user = userRepository.getUserById(id);
        if (user.isPresent()) {
            user.get().setStatus(Status.DELETED);
            user.get().setPersisted(true);

            log.info("user has been deleted...." + user.get().getId());
            userRepository.save(user.get());
        }
    }


}
