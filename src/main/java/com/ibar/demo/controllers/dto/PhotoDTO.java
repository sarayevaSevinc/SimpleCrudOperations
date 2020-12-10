package com.ibar.demo.controllers.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {
    @ApiModelProperty(notes = "photo title", example = "test title")
    String title;

    int userid;

    @ApiModelProperty(notes = "photo file")
    MultipartFile file;
}
