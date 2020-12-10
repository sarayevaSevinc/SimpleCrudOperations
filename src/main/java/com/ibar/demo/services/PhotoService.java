package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.UserResponseDTO;
import com.ibar.demo.model.Photo;
import java.io.IOException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PhotoService {

    ObjectId addPhoto(String title, MultipartFile image, long userid) throws IOException;
    Photo getPhoto(ObjectId id) throws IOException;

    String getPhotoByUserId(long id);


}
