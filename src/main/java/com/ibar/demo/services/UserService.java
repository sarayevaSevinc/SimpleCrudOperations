package com.ibar.demo.services;

import com.ibar.demo.model.User;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public interface UserService  {


    User create(User user);

    User getUserById(long id);

    User getUserByName(String name);

    User updateUser(User user);

   void deleteUserById(long id);





}
