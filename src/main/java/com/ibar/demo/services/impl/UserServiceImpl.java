package com.ibar.demo.services.impl;

import com.ibar.demo.config.securityConfig.MyPasswordEncoder;
import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhoneNumberRepository;
import com.ibar.demo.repositories.RedisUserRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.OtpService;
import com.ibar.demo.services.UserService;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.Translator;
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
    private final RedisUserRepository redisUserRepository;
    private final Translator translator;
    private final ErrorMapper errorMapper;
    private final MyPasswordEncoder myPasswordEncoder;
    private final OtpService otpService;

    public UserServiceImpl(UserRepository userRepository, PhoneNumberServiceImpl phoneService,
                           PhoneNumberRepository phoneRepository,
                           RedisUserRepository redisUserRepository, Translator translator, OtpService otpService) {
        this.userRepository = userRepository;
        this.phoneService = phoneService;
        this.phoneRepository = phoneRepository;
        this.redisUserRepository = redisUserRepository;
        this.translator = translator;
        this.errorMapper = new ErrorMapper(translator);
        this.otpService = otpService;
        this.myPasswordEncoder = new MyPasswordEncoder();
    }

    public UserResponseDTO create(UserRequestDTO user) {
        log.info("creating user service has started..");
        user.setPassword(myPasswordEncoder.passwordEncoder().encode(user.getPassword()));
        User savedUser = userRepository.save(UserMapper.INSTANCE.requestDtoToUser(user));
        phoneService.save(phoneService.createPhoneNumber(user.getPhone(), savedUser));
        //this.redisUserRepository.addUser(savedUser);
        List<PhoneNumber> byUserId = phoneRepository.findByUserId(savedUser.getId());
        otpService.sendOtp(savedUser);
        log.info("user has created with " + savedUser.getId() + " id");
        log.info("creating user service has endded...");

        return UserMapper.INSTANCE.mapUsertoUserDTO(savedUser, byUserId);

    }

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        log.info("saving user service has started..");
        Optional<User> userByPin = userRepository.getUserByPin(userRequestDTO.getPin());
        if (userByPin.isPresent()) {
            return updateUser(userRequestDTO, userByPin.get());
        } else {
            return create(userRequestDTO);
        }
    }

    @Override
    public UserResponseDTO getUserById(long id) {
        log.info("Searching user in redis....");
        User user = redisUserRepository.getUser(id);
        if (user == null) {
            return getUserByIdFromDb(id);
        } else if (user.getStatus().equals(Status.DELETED)) {
            throw new AccountNotFoundException(errorMapper.getUserNotFoundByIdError());
        } else {
            log.info("user found in redis...");
            List<PhoneNumber> phonesByUserId = phoneRepository.findByUserId(user.getId());
            return UserMapper.INSTANCE.mapUsertoUserDTO(user, phonesByUserId);
        }
    }


    public UserResponseDTO getUserByIdFromDb(long id) {
        log.info("Searching user with " + id + " id....");
        Optional<User> userById = userRepository.getUserById(id).filter(x -> x.getStatus() != (Status.DELETED));
        if (userById.isPresent()) {
            this.redisUserRepository.addUser(userById.get());
            List<PhoneNumber> phonesByUserId = phoneRepository.findByUserId(userById.get().getId());

            return UserMapper.INSTANCE.mapUsertoUserDTO(userById.get(), phonesByUserId);
        }
        throw new AccountNotFoundException(errorMapper.getUserNotFoundByIdError());
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, User savedUser) {
        log.info("Updating user.. ");
        User user = UserMapper.INSTANCE.requestDtoToUser(userRequestDTO);
        user.setId(savedUser.getId());
        user.setCreatedTime(savedUser.getCreatedTime());
        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        User save = userRepository.save(user);

        log.info("User has been updated.. " + save.getId());
        List<PhoneNumber> phonesByUserId = phoneRepository.findByUserId(save.getId());
        return UserMapper.INSTANCE.mapUsertoUserDTO(save, phonesByUserId);
    }

    @Override
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
        throw new AccountNotFoundException(errorMapper.getUserNotFoundByIdError());
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

    @Override
    public Optional<User> getUserByPin(String pin) {
        return userRepository.getUserByPin(pin);
    }
}
