package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import java.io.IOException;
import java.util.zip.DataFormatException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public interface PhotoService {

    String addPhoto(PhotoRequestDTO photoRequestDTO) throws IOException;

    Object getPhoto(ObjectId id) throws IOException, DataFormatException;

    String getPhotoByUserId(long id);


}
