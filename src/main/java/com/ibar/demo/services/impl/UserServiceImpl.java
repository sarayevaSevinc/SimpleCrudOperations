package com.ibar.demo.services.impl;

import com.ibar.demo.controllers.dto.UserDTO;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.UserService;
import com.ibar.demo.utilities.UserMapper;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO create(User user) {

        return UserMapper.mapUserToUserDto(userRepository.save(user));
    }


    @Override
    public UserDTO getUserById(long id) {
        log.info("Searching user with " + id + " id....");
        Optional<User> userById = userRepository.getUserById(id).filter(x -> x.getStatus() != (Status.DELETED));
        if(userById.isPresent()) {
            return UserMapper.mapUserToUserDto(userById.get());
        }
     throw new IllegalArgumentException();
    }

    @Override
    public UserDTO getUserByName(String name) {
        log.info("Searching user with " + name + " name....");
        Optional<User> userByName =  userRepository.getUserByName(name).filter(x -> x.getStatus() != (Status.DELETED)) ;
         if(userByName.isPresent()) {
             return UserMapper.mapUserToUserDto(userByName.get());
         }

            throw new IllegalArgumentException("there is no user with this id");
    }

    @Override
    public UserDTO updateUser(User user) {
        log.info("Updating user.. ");

        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        log.info("User has been updated.. " +  user.toString());

        return UserMapper.mapUserToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(long id) {
        log.info("User is deleting....");

        Optional<User> user = userRepository.getUserById(id);
        if (user.isPresent()) {
            user.get().setStatus(Status.DELETED);
            user.get().setPersisted(true);

            log.info("user has been deleted...." + user.toString());
            userRepository.save(user.get());
        }
    }

    public void setProfilPicture(int id, String link) {
        log.info("setting the profil picture ...");

        Optional<User> user = userRepository.getUserById(id);
        if (user.isPresent()) {
            user.get().setProfilPhotoLink(link);

            log.info("profil picture has been set");

            updateUser(user.get());
        }
    }

    public String getProfilePicture(int id) {
        log.info("getting the profil picture ....");

        return getUserById(id).getProfilePhoto();
    }

    @Bean
    void addUser(){
        User user = User.build()
                .name("testwith mysql")
                .surname("jfbjbgrg")
                .pin("fkfkrbg")
                .build();
        create(user);
    }
}
