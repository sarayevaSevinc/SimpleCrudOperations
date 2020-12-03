package com.ibar.demo.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String name;
    private String surname;
    private int age;
    private String profilePhoto;

}
