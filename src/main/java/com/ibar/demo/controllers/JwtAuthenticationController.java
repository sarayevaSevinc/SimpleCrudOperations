package com.ibar.demo.controllers;

import static com.ibar.demo.constants.StaticVariable.LOGIN_MESSAGE;
import com.ibar.demo.config.securityConfig.JwtTokenUtil;
import com.ibar.demo.config.securityConfig.MyPasswordEncoder;
import com.ibar.demo.controllers.dto.JwtRequestDTO;
import com.ibar.demo.controllers.dto.JwtResponseDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.JwtUserDetailsService;
import com.ibar.demo.services.TokenService;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.Translator;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Log4j2
public class JwtAuthenticationController {


    UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private final Translator translator;
    private final ErrorMapper errorMapper;
    private final TokenService tokenService;
    private final MyPasswordEncoder passwordEncoder;

    public JwtAuthenticationController(UserRepository userRepository,
                                       AuthenticationManager authenticationManager,
                                       JwtTokenUtil jwtTokenUtil,
                                       JwtUserDetailsService userDetailsService,
                                       Translator translator, TokenService tokenService,
                                       MyPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.translator = translator;
        this.errorMapper = new ErrorMapper(translator);
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDTO authenticationRequest) throws Exception {
      log.info(authenticationRequest.getPassword());
      log.info(authenticationRequest.getUsername());
         Optional<User> user = userRepository.getUserByPin(authenticationRequest.getUsername());

        if(!user.isPresent()){
            log.info("i am here");
            throw new AccountNotFoundException(errorMapper.getUserNotFoundByNameError());
        }
        log.info(user.get().getPassword());
        log.info(passwordEncoder.passwordEncoder().encode(authenticationRequest.getPassword()));
        if(!user.get().getPassword().equals(passwordEncoder.passwordEncoder().encode(authenticationRequest.getPassword()))){
            log.info("i am here 2");
            throw new AccountNotFoundException(errorMapper.getPhoneNumberNotFoundWithIDError());
        }
        tokenService.sendOtp(user.get());

        return ResponseEntity.ok(new JwtResponseDTO(LOGIN_MESSAGE));
    }
}