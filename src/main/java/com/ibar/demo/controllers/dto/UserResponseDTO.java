package com.ibar.demo.controllers.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    @ApiModelProperty(notes = "user name", example = "Eli")
    private String name;

    @ApiModelProperty(notes = "user surname", example = "Eliyev")
    private String surname;

    @ApiModelProperty(notes = "user age", example = "20")
    private int age;

    @ApiModelProperty(notes = "user's phone numbers list", example = "[ { +994778785641 } ] ")
    private List<PhoneNumberDTO> phone_numbers;

    @ApiModelProperty(notes = "user birthday", example = "27-07-2000")
    private String birthday;

    @ApiModelProperty(notes = "user's gender", example = "W")
    private String profile_image;
}
