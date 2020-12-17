package com.ibar.demo.controllers.dto;

import com.ibar.demo.annotation.CardNumberConstraint;
import com.ibar.demo.annotation.GenderConstraint;
import com.ibar.demo.annotation.IsValidConstraint;
import com.ibar.demo.annotation.NameConstraint;
import com.ibar.demo.annotation.PhoneNumberConstraint;
import com.ibar.demo.annotation.PinConstraint;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
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

    @NameConstraint
    @ApiModelProperty(notes = "user name", example = "Eli")
    private String name;

    @NameConstraint
    @ApiModelProperty(notes = "user surname", example = "Eliyev")
    private String surname;

    @ApiModelProperty(notes = "user age", example = "20")
    private int age;


    @ApiModelProperty(notes = "user birthday", example = "01.01.1999")
    private String birthday;


    //@IsValidConstraint
    @PinConstraint
    @ApiModelProperty(notes = "user's pin ", example = "124354546")
    private String pin;

    @CardNumberConstraint
    @ApiModelProperty(notes = "user's card Number", example = "124354546")
    private String card_number;


    @ApiModelProperty(notes = "user's phone number", example = "+99477357458")
    @PhoneNumberConstraint
    private String phone;

    @GenderConstraint
    @ApiModelProperty(notes = "user's gender", example = "W")
    private String gender;


}
