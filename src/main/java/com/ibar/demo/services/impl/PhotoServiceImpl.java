package com.ibar.demo.services.impl;


import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.PhotoNotFound;
import com.ibar.demo.model.Photo;
import com.ibar.demo.model.StaticVariable;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.PhotoRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.services.PhotoService;
import com.ibar.demo.utilities.ErrorMapper;
import java.io.IOException;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    public ObjectId addPhoto(String title, MultipartFile image, long userid) throws IOException {
        Optional<User> userById = userRepository.getUserById(userid);
        if (userById.isPresent()) {

            Photo photo = Photo.builder()
                    .title(title)
                    .userId(userid)
                    .data(image.getBytes())
                    .build();

            ObjectId id = this.photoRepository.save(photo).getId();

            updateUserAfterAddingPhoto(userById.get(), getUrl(id));

            return id;
        }

        throw new AccountNotFoundException(ErrorMapper.getUserNotFoundByIdError());
    }

    public void updateUserAfterAddingPhoto(User user, String url) {

        user.setProfilePictureUrl(url);
        user.setStatus(Status.UPDATED);
        user.setPersisted(true);

        this.userRepository.save(user);
    }

    @Override
    public Photo getPhoto(ObjectId id) throws IOException {
        return photoRepository.findById(id).orElseThrow(()-> new PhotoNotFound(ErrorMapper.getProfilePhotoNotFoundByIdError()));
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
                .path("/images/")
                .path("/" + StaticVariable.lang + "/")
                .path(id.toString())
                .toUriString();
    }
}


