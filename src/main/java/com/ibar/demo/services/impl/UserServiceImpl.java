package com.ibar.demo.services.impl;

import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }


    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id).filter(user -> user.getStatus() != Status.DELETED)
                .orElseThrow(() -> new IllegalArgumentException("there is no any user with this id"));
    }

    @Override
    public User getUserByName(String name) {
 return userRepository.getUserByName(name).filter(x->x.getStatus()!=(Status.DELETED))
         .orElseThrow(()->new IllegalArgumentException("there is no user with this id"));

    }

    @Override
    public User updateUser(User user) {
        user.setStatus(Status.UPDATED);
        return create(user);
    }

    @Override
    public void deleteUserById(int id) {
        User user = getUserById(id);
        user.setStatus(Status.DELETED);
        create(user);
    }


    @Bean
    public void addUser() {
        User build = new User.UserBuilder("name", "surname", "caifef", "efeof", "4854958")
                .build();

        create(build);
    }
//    @Bean
//    public void getUser(){
//        System.out.println(getUserbyName("test7").toString());;
//    }
//    @Bean
//    public void updateUser(){
//       User user = getUserById(1);
//       user.setName("updatedUser");
//        updateUser(user);
//    }
//    @Bean
//    public void deleteUser(){
//        deleteUserById(1);
//    }
}
