package com.ibar.demo.services;

import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.model.User;
import com.ibar.demo.services.impl.UserServiceImpl;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.Translator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class JwtUserDetailsService implements UserDetailsService {


    private final UserServiceImpl userService;
    private final Translator translator;
    private final ErrorMapper errorMapper;

    @Autowired
    public JwtUserDetailsService(UserServiceImpl userService, Translator translator) {

        this.userService = userService;
        this.translator = translator;
        this.errorMapper = new ErrorMapper(translator);
    }

    @Override
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        Optional<User> userByUsername = userService.getUserByPin(pin);
        if (userByUsername.isPresent()) {
            return userByUsername.get();
        }
        throw  new AccountNotFoundException(errorMapper.getUserNotFoundByNameError());
    }
}
