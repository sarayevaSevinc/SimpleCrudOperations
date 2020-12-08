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

@Service
public class PhotoServiceImpl  implements PhotoService {

    private final PhotoRepository photoRepository;


    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public ObjectId addPhoto(String title, MultipartFile image) throws IOException {
        Photo photo = Photo.builder()
                .title(title)
                .build();
        photo.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));

        return this.photoRepository.save(photo).getId();
//        DBObject metaData = new BasicDBObject();
//        metaData.put("type", "video");
//        metaData.put("title", title);
//        ObjectId id = gridFsTemplate.store(
//                image.getInputStream(), image.getName(), image.getContentType(), metaData);
//        return id.toString();
    }

    @Override
    public Photo getPhoto(ObjectId id) throws IOException {
       // photoRepository.findByID(id);
            return photoRepository.findById(id).get();
        }
    }


