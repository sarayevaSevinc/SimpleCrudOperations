package com.ibar.demo.services;


import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService  {


    User create(User user);

    User getUserbyId(UUID id);

     Optional<User> getUserbyName(String name);
     User updateUser(User user);

   void deleteUserById(UUID id);





}
