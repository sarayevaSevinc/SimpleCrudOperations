package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import com.ibar.demo.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public class ProfilePhotoMapper {
    ProfilePhotoMapper INSTANCE = Mappers.getMapper(ProfilePhotoMapper.class);
    public static Photo photoDtoToPhoto(PhotoRequestDTO photoRequestDTO) {
        if ( photoRequestDTO == null ) {
            return null;
        }

        Photo photo = new Photo();

        photo.setTitle( photoRequestDTO.getTitle() );
        photo.setData( photoRequestDTO.getData() );

        return photo;
    }
}
