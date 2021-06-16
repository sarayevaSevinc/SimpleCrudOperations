package com.ibar.demo.utilities;

import com.ibar.demo.controllers.dto.PhotoRequestDTO;
import com.ibar.demo.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfilePhotoMapper {
    ProfilePhotoMapper INSTANCE = Mappers.getMapper(ProfilePhotoMapper.class);
    Photo photoDtoToPhoto(PhotoRequestDTO photoRequestDTO);
}
