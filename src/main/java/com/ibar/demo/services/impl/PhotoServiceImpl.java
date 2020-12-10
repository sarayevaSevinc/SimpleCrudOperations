package com.ibar.demo.services.impl;

import com.ibar.demo.model.Photo;
import com.ibar.demo.repositories.PhotoRepository;
import com.ibar.demo.services.PhotoService;
import java.io.IOException;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class PhotoServiceImpl  implements PhotoService {

    private final PhotoRepository photoRepository;


    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public ObjectId addPhoto(String title, MultipartFile image, int userid) throws IOException {
        Photo photo = Photo.builder()
                .title(title)
                .userId(userid)
                .build();
        photo.setData( image.getBytes());

        return this.photoRepository.save(photo).getId();
    }

    @Override
    public Photo getPhoto(ObjectId id) throws IOException {
       // photoRepository.findByID(id);
            return photoRepository.findById(id).get();
        }

    @Override
    public Photo getPhotoByUserId(int id) {
        return photoRepository.findByUserId(id).get();
    }


}


