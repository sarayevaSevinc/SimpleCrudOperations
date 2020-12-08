package com.ibar.demo.controllers.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
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
public class UserDTO {

    @ApiModelProperty(notes = "user name", example = "Eli")
    private String name;

    @ApiModelProperty(notes = "user surname", example = "Eliyev")
    private String surname;

    @ApiModelProperty(notes = "user age", example = "20")
    private int age;

    @ApiModelProperty(notes = "user's phone numbers list", example = "[ { +994774081550 } ] ")
    private List<PhoneNumberDTO> phoneNumbers;

    @ApiModelProperty(notes = "user birthday", example = "27-07-2000")
    private LocalDate birthday;

}
