package com.ibar.demo.services.impl;


import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.PhotoNotFound;
import com.ibar.demo.model.Photo;
import com.ibar.demo.model.StaticVariable;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhotoRepository;
import com.ibar.demo.repositories.RedisRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.PhotoService;
import com.ibar.demo.utilities.Compressor;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.ProfilePhotoMapper;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Log4j2
@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final RedisRepository redisRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, UserRepository userRepository,RedisRepository redisRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.redisRepository = redisRepository;

    }

    @Override
    public String addPhoto(PhotoRequestDTO requestDto) {
        log.info("adding profile photo service has started...");
        Optional<User> userById = userRepository.getUserById(requestDto.getUser_id());
        if (userById.isPresent()) {
            Photo photo = ProfilePhotoMapper.INSTANCE.photoDtoToPhoto(requestDto);
            photo.setUserId(requestDto.getUser_id());

            Photo save = this.photoRepository.save(photo);

            String url = getUrl(save.getId());

            updateUserAfterAddingPhoto(userById.get(), url);
            log.info("adding profile photo service has ended...");
            return url;
        }

        throw new AccountNotFoundException(ErrorMapper.getUserNotFoundByIdError());
    }

    public void updateUserAfterAddingPhoto(User user, String url) {
        log.info("updating user after adding photo service has started...");
        user.setProfile_picture_url(url);
        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        this.userRepository.save(user);
        this.redisRepository.addUser(user);
        log.info("updating user after adding photo service has ended...");
    }

    @Override
    public byte[] getPhoto(ObjectId id) throws IOException, DataFormatException {
        log.info("getting photo service has started...");
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFound(ErrorMapper.getProfilePhotoNotFoundByIdError()));
        return Compressor.decompress(photo.getData().getData());
    }


    @Override
    public String getPhotoByUserId(long id) {
        if (userRepository.getUserById(id).isPresent()) {
            return getPhotoUrl(id);
        }
        throw new AccountNotFoundException(ErrorMapper.getUserNotFoundByIdError());

    }

    public String getPhotoUrl(long id) {
        Optional<Photo> photo = photoRepository.findByUserId(id);
        if (photo.isPresent()) {
            return getUrl(photo.get().getId());
        }

        return "There is no profile picture for this user.";

    }

    public String getUrl(ObjectId id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/")
                .path("/" + StaticVariable.lang + "/")
                .path("/images/")
                .path(id.toString())
                .toUriString();
    }
}


