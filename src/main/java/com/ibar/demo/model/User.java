package com.ibar.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
@JsonDeserialize(as = User.class)
@Builder
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String surname;

    private int age;

    private String profile_picture_url;

    private LocalDate birthday;


    private String pin;

    private String email;

    private String password;
}


