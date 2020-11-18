package com.ibar.demo.service;


import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
     void getUserByIdTest() {
        // set up
        User user = new User.UserBuilder("test3", "test4", "cardnumber", "45845h", "phone")
                .setId(5)
                .setAge(20)
                .build();
        Mockito.when(userRepository.getUserById(5)).thenReturn(Optional.of(user));
        User userbyId = userService.getUserById(5);
        Assertions.assertEquals(userbyId, user);
    }

    @Test
    void getUserByNameTest(){
        // set up
        User user = new User.UserBuilder("test3", "test4", "cardnumber", "45845h", "phone")
                .setId(5)
                .setAge(20)
                .build();
        Mockito.when(userRepository.getUserByName("test3")).thenReturn(Optional.of(user));
        User actualResult = userService.getUserByName("test3");
        Assertions.assertEquals(actualResult, user);
    }
 @Test
    void updateUserTest(){
     // set up
     User user = new User.UserBuilder("test3", "test4", "cardnumber", "45845h", "phone")
             .setId(5)
             .setAge(20)
             .build();
     Mockito.when(userRepository.save(user)).thenReturn(user);
     User actualResult = userService.updateUser(user);
     Assertions.assertEquals(actualResult, user);
 }


}
