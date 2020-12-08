package com.ibar.demo.controllers.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Past;
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
public class UserRequestDTO {

    @ApiModelProperty(notes = "user name", example = "Eli")
    private String name;

    @ApiModelProperty(notes = "user surname", example = "Eliyev")
    private String surname;

    @ApiModelProperty(notes = "user age", example = "20")
    private int age;

    @Past
    @ApiModelProperty(notes = "user birthday", example = "01.01.1999")
    private String birthday;

    @ApiModelProperty(notes = "user's pin ", example = "124354546")
    private String pin;

    @ApiModelProperty(notes = "user's card Number", example = "124354546")
    private String cardNumber;

    @ApiModelProperty(notes = "user's phone number", example = "+994774081550")
    private String phone;

    @ApiModelProperty(notes = "user's gender", example = "W")
    private String gender;

}
