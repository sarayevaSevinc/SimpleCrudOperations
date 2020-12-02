package com.ibar.demo.services.impl;

import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }


    @Override
    public User getUserById(long id) {
        log.info("Searching user with " + id +" id....");
        return userRepository.getUserById(id).filter(user -> user.getStatus() != Status.DELETED)
                .orElseThrow(() -> new IllegalArgumentException("there is no any user with this id"));
    }

    @Override
    public User getUserByName(String name) {
        log.info("Searching user with " +name +" name....");
 return userRepository.getUserByName(name).filter(x->x.getStatus()!=(Status.DELETED))
         .orElseThrow(()->new IllegalArgumentException("there is no user with this id"));

    }

    @Override
    public User updateUser(User user) {

        log.info("Updating user.. ");

        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        log.info("User has been updated..");

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        log.info("User is deleting....");
        User user = getUserById(id);
        user.setStatus(Status.DELETED);
        user.setPersisted(true);
        log.info("user has been deleted....");
        userRepository.save(user);
    }

    public void setProfilPicture(int id, String link){
        User user = getUserById(id);

        log.info("setting the profil picture ...");
        user.setProfilPhotoLink(link);
        log.info("profil picture has been set");
        updateUser(user);
    }

    public String getProfilPicture(int id){
        log.info("getting the profil picture ....");
        return getUserById(id).getProfilPhotoLink();
    }

}
