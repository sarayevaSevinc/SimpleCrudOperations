package com.ibar.demo.services.impl;

import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.IdDoesNotEqualException;
import com.ibar.demo.exceptions.PhotoNotFound;
import com.ibar.demo.model.Photo;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhotoRepository;
import com.ibar.demo.repositories.RedisUserRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.PhotoService;
import com.ibar.demo.utilities.Compressor;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.ProfilePhotoMapper;
import com.ibar.demo.utilities.Translator;
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
    private final RedisUserRepository redisUserRepository;
    private final ErrorMapper errorMapper;

    public PhotoServiceImpl(PhotoRepository photoRepository, UserRepository userRepository,
                            RedisUserRepository redisUserRepository,
                            Translator translator) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.redisUserRepository = redisUserRepository;
        this.errorMapper = new ErrorMapper(translator);
    }

    @Override
    public String addPhoto(PhotoRequestDTO requestDto, long id) {
        log.info("adding profile photo service has started...");
        if (requestDto.getUser_id() != id) {
            throw new IdDoesNotEqualException(errorMapper.getIdDoesNotEqualError());
        }
        Optional<User> userById = userRepository.getUserById(requestDto.getUser_id());
        if (!userById.isPresent()) {
            throw new AccountNotFoundException(errorMapper.getUserNotFoundByIdError());
        }

        Photo photo = ProfilePhotoMapper.INSTANCE.photoDtoToPhoto(requestDto);
        photo.setUserId(requestDto.getUser_id());

        Photo save = this.photoRepository.save(photo);

        String url = getUrl(save.getId());

        updateUserAfterAddingPhoto(userById.get(), url);
        log.info("adding profile photo service has ended...");
        return url;
    }


    public void updateUserAfterAddingPhoto(User user, String url) {
        log.info("updating user after adding photo service has started...");
        user.setProfile_picture_url(url);
        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        this.userRepository.save(user);
        this.redisUserRepository.addUser(user);
        log.info("updating user after adding photo service has ended...");
    }

    @Override
    public byte[] getPhoto(ObjectId id) throws IOException, DataFormatException {
        log.info("getting photo service has started...");
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFound(errorMapper.getProfilePhotoNotFoundByIdError()));
        return Compressor.decompress(photo.getData().getData());
    }


    @Override
    public String getPhotoByUserId(long id) {
        if (userRepository.getUserById(id).isPresent()) {
            return getPhotoUrl(id);
        }
        throw new AccountNotFoundException(errorMapper.getUserNotFoundByIdError());

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
                .path("/images/")
                .path(id.toString())
                .toUriString();
    }
}


