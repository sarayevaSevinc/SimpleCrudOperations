package com.ibar.demo.services.impl;


import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.PhotoNotFound;
import com.ibar.demo.model.Photo;
import com.ibar.demo.model.StaticVariable;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhotoRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.PhotoService;
import com.ibar.demo.utilities.Compressor;
import com.ibar.demo.utilities.ErrorMapper;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String addPhoto(PhotoRequestDTO requestDto) {
        Optional<User> userById = userRepository.getUserById(requestDto.getUser_id());
        if (userById.isPresent()) {
            Photo photo = Photo.builder()
                    .title(requestDto.getTitle())
                    .userId(requestDto.getUser_id())
                    .data(requestDto.getData())
                    .build();

            Photo save = this.photoRepository.save(photo);
            String url = getUrl(save.getId());

            updateUserAfterAddingPhoto(userById.get(), url);

            return url;
        }

        throw new AccountNotFoundException(ErrorMapper.getUserNotFoundByIdError());
    }

    public void updateUserAfterAddingPhoto(User user, String url) {

        user.setProfile_picture_url(url);
        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        this.userRepository.save(user);
    }

    @Override
    public byte[] getPhoto(ObjectId id) throws IOException, DataFormatException {
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


