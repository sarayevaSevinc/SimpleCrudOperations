package com.ibar.demo.services.impl;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhoneNumberRepository;
import com.ibar.demo.repositories.RedisRepository;
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

    private final UserRepository userRepository;
    private final PhoneNumberServiceImpl phoneService;
    private final PhoneNumberRepository phoneRepository;
    private final RedisRepository redisRepository;


    public UserServiceImpl(UserRepository userRepository, PhoneNumberServiceImpl phoneService,
                           PhoneNumberRepository repository, RedisRepository redisRepository) {

        this.userRepository = userRepository;
        this.phoneService = phoneService;
        this.phoneRepository = repository;
        this.redisRepository = redisRepository;
    }


    @Override
    public UserResponseDTO create(UserRequestDTO user) {
        log.info("creating user service has started..");
        User savedUser = userRepository.save(UserMapper.INSTANCE.requestDtoToUser(user));
        phoneService.save(phoneService.createPhoneNumber(user.getPhone(), savedUser));
        this.redisRepository.addUser(savedUser);
        List<PhoneNumber> byUserId = phoneRepository.findByUserId(savedUser.getId());
        this.redisRepository.getAllUser();
        log.info("creating user service has endded...");
        log.info(savedUser.toString());
        return UserMapper.INSTANCE.mapUsertoUserDTO(savedUser, byUserId);

    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        log.info("saving user service has started..");
        if (userRepository.getUserByPin(userRequestDTO.getPin()).isPresent()) {
            return updateUser(userRequestDTO);
        } else {
            return create(userRequestDTO);
        }
    }


    public UserResponseDTO getUserById2(long id) {
        log.info("Searching user in redis....");
        User user = redisRepository.getUser(id);
        if (user == null) {
        return  getUserById(id);
        } else {
            log.info("user found in redis...");
            List<PhoneNumber> phonesByUserId = phoneRepository.findByUserId(user.getId());
            return UserMapper.INSTANCE.mapUsertoUserDTO(user, phonesByUserId);
        }
    }

    @Override
    public UserResponseDTO getUserById(long id) {
        log.info("Searching user with " + id + " id....");
        Optional<User> userById = userRepository.getUserById(id).filter(x -> x.getStatus() != (Status.DELETED));
        if (userById.isPresent()) {
            this.redisRepository.addUser(userById.get());
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
        User user = UserMapper.INSTANCE.requestDtoToUser(userRequestDTO);
        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        log.info("User has been updated.. " + user.getId());

        User save = userRepository.save(user);
        List<PhoneNumber> phonesByUserId = phoneRepository.findByUserId(save.getId());
        return UserMapper.INSTANCE.mapUsertoUserDTO(save, phonesByUserId);
    }

    public UserResponseDTO addUserPhoneNumber(int id, PhoneNumberDTO number) {
        Optional<User> userById = userRepository.getUserById(id);
        log.info("adding phone number service has started...");
        if (userById.isPresent()) {

            phoneService.save(phoneService.createPhoneNumber(number.getPhone(), userById.get()));
            List<PhoneNumber> phones = phoneRepository.findByUserId(userById.get().getId());
            UserResponseDTO userResponseDTO = UserMapper.INSTANCE.mapUsertoUserDTO(userById.get(), phones);

            log.info("adding phone number service has endded...");
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
