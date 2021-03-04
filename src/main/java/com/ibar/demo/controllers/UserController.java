package com.ibar.demo.controllers;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

import com.ibar.demo.constants.StaticVariable;
import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import com.ibar.demo.controllers.dto.ResponseDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.model.User;
import com.ibar.demo.services.PhotoService;
import com.ibar.demo.services.TokenService;
import com.ibar.demo.services.UserService;
import com.ibar.demo.utilities.Compressor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.zip.DataFormatException;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Log4j2
@RestController
@Validated
@RequestMapping(path = {"/users", "/user", "/api/"})
@Api(value = "Simple Crud Api")
public class UserController {

    private final UserService service;
    private final PhotoService photoService;
    private final TokenService tokenService;

    public UserController(UserService service, PhotoService photoService, TokenService tokenService) {

        this.service = service;
        this.photoService = photoService;
        this.tokenService = tokenService;
    }

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }

    @ApiOperation(value = "user", notes = "Get user account by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @GetMapping("/getUser")
    public ResponseDTO<UserResponseDTO> getUser(@RequestParam long id,
                                                @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az")
                                                        String lang) {
        StaticVariable.lang = lang;
        log.info("getting user with " + id + " id .....");
        return ResponseDTO.<UserResponseDTO>builder()
                .data(service.getUserById(id))
                .build();
    }

    @ApiOperation(value = "user", notes = "Adding user to mongo db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/add")
    public ResponseDTO<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO user,
                                                @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az")
                                                        String lang) {
        log.info("adding user ......");

        StaticVariable.lang = lang;
        return ResponseDTO.<UserResponseDTO>builder()
                .data(service.saveUser(user))
                .build();
    }

    @ApiOperation(value = "user", notes = "Adding profile picture to db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = String.class)})
    @PostMapping(value = "/addProfilePhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO<String> addPhoto(@RequestPart("photo") PhotoRequestDTO photo,
                                        @RequestPart("file") MultipartFile file,
                                        Authentication auth,
                                        @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az") String lang)
            throws IOException {

        StaticVariable.lang = lang;
        User user = (User) auth.getPrincipal();
        photo.setData(new Binary(BsonBinarySubType.BINARY, Compressor.compress(file.getBytes())));
        String url = photoService.addPhoto(photo, user.getId());

        return ResponseDTO.<String>builder()
                .data(url)
                .build();
    }

    @ApiOperation(value = "image", notes = "getting image")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = byte.class)})
    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseDTO<byte[]> getFile(@RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az") String lang,
                                       @PathVariable ObjectId id) throws IOException, DataFormatException {
        StaticVariable.lang = lang;
        return ResponseDTO.<byte[]>builder()
                .data(photoService.getPhoto(id))
                .build();
    }

    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/update")
    public ResponseDTO<UserResponseDTO> updateUser(
            @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az") String lang,
            @RequestBody UserRequestDTO user) {

        StaticVariable.lang = lang;
        log.info("updating user ......");

        return ResponseDTO.<UserResponseDTO>builder()
                .data(service.saveUser(user))
                .build();
    }

    @ApiOperation(value = "user", notes = "verify Otp")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/verifyOtp")
    public ResponseDTO<?> verifyOtp(
            @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az") String lang,
            @RequestParam long userId,
            @RequestParam String otp) {

        StaticVariable.lang = lang;
        log.info("updating user ......");

        return ResponseDTO.builder()
                .data(tokenService.verifyOTP(userId, otp))
                .build();
    }

    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/addPhoneNumber")
    public ResponseDTO<UserResponseDTO> addUserPhoneNumber(@RequestParam int id,
                                                           @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az")
                                                                   String lang,
                                                           @RequestBody PhoneNumberDTO number) {

        StaticVariable.lang = lang;

        log.info("updating user ......");

        return ResponseDTO.<UserResponseDTO>builder()
                .data(service.addUserPhoneNumber(id, number))
                .build();
    }
}
