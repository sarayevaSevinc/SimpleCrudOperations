package com.ibar.demo.services.impl;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.exceptions.UserNotFoundEx;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.UserService;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.UserMap;
import com.ibar.demo.utilities.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PhoneNumberServiceImpl phoneService;



    public UserServiceImpl(UserRepository userRepository, PhoneNumberServiceImpl phoneService) {

        this.userRepository = userRepository;
        this.phoneService = phoneService;

    }


    @Override
    public UserDTO create(UserRequestDTO user) {
        User save = userRepository.save(UserMap.INSTANCE.requestDtoToUser(user));
        phoneService.save(PhoneNumber.builder()
        .user(save)
        .phone(user.getPhone())
        .build());
        return getUserById(save.getId());
    }


    @Override
    public UserDTO getUserById(long id) {
        log.info("Searching user with " + id + " id....");
        Optional<User> userById = userRepository.getUserById(id).filter(x -> x.getStatus() != (Status.DELETED));
        if (userById.isPresent()) {
            return UserMapper.addPhoneNumberToUserDto(UserMap.INSTANCE.userToUserDTO(userById.get()),
                    phoneService.getPhoneNumberByUser(userById.get().getId()));
        }
        throw new UserNotFoundEx(ErrorMapper.getUserNotFoundByIdError());
    }

    @Override
    public UserDTO getUserByName(String name) {
        log.info("Searching user with " + name + " name....");

        Optional<User> userByName = userRepository.getUserByName(name).filter(x -> x.getStatus() != (Status.DELETED));

        if (userByName.isPresent()) {

            return UserMapper.addPhoneNumberToUserDto(UserMap.INSTANCE.userToUserDTO(userByName.get()),
                    phoneService.getPhoneNumberByUser(userByName.get().getId()));
        }
        throw new UserNotFoundEx(ErrorMapper.getUserNotFoundByNameError());
    }

    @Override
    public UserDTO updateUser(User user) {
        log.info("Updating user.. ");

        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        log.info("User has been updated.. " + user.getId());

        return UserMap.INSTANCE.userToUserDTO(userRepository.save(user));
    }

    public UserDTO addUserPhoneNumber(int id, String number) {
        Optional<User> userById = userRepository.getUserById(id);

        if (userById.isPresent()) {

            PhoneNumber build = PhoneNumber.builder()
                    .phone(number)
                    .user(userById.get())
                    .build();

            log.info(build.getPhone());
            phoneService.save(build);

            List<PhoneNumberDTO> save = phoneService.getPhoneNumberByUser(userById.get().getId());
            UserDTO userDTO = UserMap.INSTANCE.userToUserDTO(userById.get());
            UserMapper.addPhoneNumberToUserDto(userDTO, save);
            return userDTO;

        }
        throw new UserNotFoundEx(ErrorMapper.getUserNotFoundByIdError());
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
