package com.ibar.demo.controllers;

import com.ibar.demo.controllers.dto.PhoneNumberDTO;
import com.ibar.demo.controllers.dto.UserRequestDTO;
import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.model.PhoneNumber;
import com.ibar.demo.model.Photo;
import com.ibar.demo.model.StaticVariable;
import com.ibar.demo.model.User;
import com.ibar.demo.services.impl.PhotoServiceImpl;
import com.ibar.demo.services.impl.UserServiceImpl;
import com.ibar.demo.utilities.LanguageMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/{lang}/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String lang,
                                                   @PathVariable long id) {
        LanguageMapper.chooseLang(lang);
        log.info("getting user with " + id + " id .....");
        return new ResponseEntity(service.getUserById(id), HttpStatus.OK);
    }


    @ApiOperation(value = "user", notes = "Adding user to mongo db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/{lang}/add")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO user,
                                                   @PathVariable String lang) {
        log.info("adding user ......");

        LanguageMapper.chooseLang(lang);
        return new ResponseEntity<>(service.create(user), HttpStatus.OK);
    }

    @ApiOperation(value = "user", notes = "Adding profil picture to db")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = String.class)})
    @PostMapping("/{lang}/addProfilePhoto/{id}")
    public ResponseEntity<UserResponseDTO> addPhoto(@PathVariable String lang,
                                                    @PathVariable int id,
                                                    @RequestPart String title,
                                                    @RequestPart MultipartFile file)
            throws IOException {
        log.info(title);
        log.info(file);
        LanguageMapper.chooseLang(lang);
        photoService.addPhoto(title, file, id);
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "image", notes = "getting image")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = byte.class)})
    @GetMapping("/{lang}/images/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String lang,
                                          @PathVariable ObjectId id) throws IOException {
        LanguageMapper.chooseLang(lang);
        Photo photo = photoService.getPhoto(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getTitle() + "\"")
                .body(photo.getData());
    }

    @ApiOperation(value = "getting the profile picture", notes = "Get user profile picture by user id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = Photo.class)})
    @GetMapping("/{lang}/getProfilePhoto/{id}")
    public byte[] getPhoto(@PathVariable ObjectId id,
                           @PathVariable String lang) throws IOException {

        LanguageMapper.chooseLang(lang);
        Photo photo = photoService.getPhoto(id);
        return photo.getData();
    }


    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/{lang}/update")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String lang,
                                                      @RequestBody UserRequestDTO user) {

        LanguageMapper.chooseLang(lang);
        log.info("updating user ......");

        return new ResponseEntity<>(service.updateUserByRequestDTO(user), HttpStatus.OK);
    }

    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/{lang}/addPhoneNumber")
    public ResponseEntity<UserResponseDTO> addUserPhoneNumber(@PathVariable String lang,
                                                              @RequestParam int id,
                                                              @RequestBody PhoneNumberDTO number) {

        LanguageMapper.chooseLang(lang);

        log.info("updating user ......");

        return new ResponseEntity<>(service.addUserPhoneNumber(id, number), HttpStatus.OK);
    }
}
