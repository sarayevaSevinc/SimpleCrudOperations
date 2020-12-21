package com.ibar.demo.controllers;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.model.StaticVariable;
import com.ibar.demo.model.User;
import com.ibar.demo.services.impl.PhotoServiceImpl;
import com.ibar.demo.services.impl.UserServiceImpl;
import com.ibar.demo.utilities.Compressor;
import com.ibar.demo.utilities.LanguageMapper;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final UserServiceImpl service;
    private final PhotoServiceImpl photoService;

    public UserController(UserServiceImpl service, PhotoServiceImpl photoService) {

        this.service = service;
        this.photoService = photoService;
    }

    @ApiOperation(value = "user", notes = "Get user account by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam String lang,
                                                   @RequestParam long id) {
        LanguageMapper.chooseLang(lang);
        log.info("getting user with " + id + " id .....");
        return new ResponseEntity(service.getUserById(id), HttpStatus.OK);
    }


    @ApiOperation(value = "user", notes = "Adding user to mongo db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO user,
                                                   @RequestParam String lang) {
        log.info("adding user ......");

        LanguageMapper.chooseLang(lang);
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.OK);
    }

    @ApiOperation(value = "user", notes = "Adding profil picture to db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = String.class)})
    @PostMapping(value = "/addProfilePhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addPhoto(@RequestParam String lang,
                                           @RequestPart("photo") PhotoRequestDTO photo,
                                           @RequestPart("file") MultipartFile file)
            throws IOException {

        LanguageMapper.chooseLang(lang);

        photo.setData(new Binary(BsonBinarySubType.BINARY, Compressor.compress(file.getBytes())));
        String url = photoService.addPhoto(photo);

        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @ApiOperation(value = "image", notes = "getting image")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = byte.class)})
    @GetMapping(value = "{lang}/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getFile(@PathVariable String lang,
                          @PathVariable ObjectId id) throws IOException, DataFormatException {
        LanguageMapper.chooseLang(lang);

        return photoService.getPhoto(id);
    }

    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestParam String lang,
                                                      @RequestBody UserRequestDTO user) {

        LanguageMapper.chooseLang(lang);
        log.info("updating user ......");

        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }

    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/addPhoneNumber")
    public ResponseEntity<UserResponseDTO> addUserPhoneNumber(@RequestParam String lang,
                                                              @RequestParam int id,
                                                              @RequestBody PhoneNumberDTO number) {

        LanguageMapper.chooseLang(lang);

        log.info("updating user ......");

        return new ResponseEntity<>(service.addUserPhoneNumber(id, number), HttpStatus.OK);
    }
}
