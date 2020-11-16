package com.ibar.demo.services.impl;

import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl  implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user){
        return userRepository.save(user);
    }

    public User getUserbyId(UUID id){
        return  userRepository.getUserById(id).filter(user -> user.getStatus()!= Status.DELETED)
                .orElseThrow(()->new IllegalArgumentException("there is no any user with this id"));
    }

    public Optional<User> getUserbyName(String name){

        return userRepository.getUserByName(name);
    }
    public User updateUser(User user){
        user.setStatus(Status.UPDATED);
        return create(user);
    }

    public void deleteUserById(UUID id){
        User user = getUserbyId(id);
        user.setStatus(Status.DELETED);
        create(user);
    }



    @Bean
    public  void  addUser(){
        User build = User.builder().name("test7")
                .surname("trst3rf")
                .cardNumber("95748574")
                .pin("45784rh4ut")
                .phone("49595475p")
                .build();

        create(build);
    }
//    @Bean
//    public void getUser(){
//        System.out.println(getUserbyName("test7").toString());;
//    }
//    @Bean
//    public void updateUser(){
//       User user = getUserbyId(4);
//       user.setName("updatedUser");
//        updateUser(user);
//    }
//    @Bean
//    public void deleteUser(){
//        deleteUserById(7);
//    }
}
