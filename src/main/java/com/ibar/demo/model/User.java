package com.ibar.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "IBA_USERS")
public class User implements Serializable, Persistable<Long> {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @ApiModelProperty(notes = "user id", example = "123")
    @Id
    private long id;

    @ApiModelProperty(notes = "user name", example = "Eli")
    @Field("name")
    @NotNull
    private String name;

    @ApiModelProperty(notes = "user surname", example = "Eliyev")
    @Field("surname")
    private String surname;


    @ApiModelProperty(notes = "user age", example = "20")
    @Field("age")
    private int age;


    @ApiModelProperty(notes = "user birthday", example = "27-07-2000")
    @Field("birthday")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;

    @ApiModelProperty(notes = "user pin", example = "AZE24347855")
    @Field("pin")
    @NotNull
    private String pin;

    @ApiModelProperty(notes = "user card number", example = "1234567891234567")
    @Field("cardNumber")
    private String cardNumber;


    @ApiModelProperty(notes = "user gender", example = "WOMAN")
    @Field("gender")
    private String gender;

    @ApiModelProperty(notes = "user phone number", example = "+994501234567")
    @Field("phone")
    private String phone;

    @ApiModelProperty(notes = "user account status", example = "CREATED")
    @Field("status")
    private Status status;


    @ApiModelProperty(notes = "account created time", example = "02-12-20200 16:57")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @CreatedDate
    @Field("createdTime")
    @PastOrPresent
    private LocalDateTime createdTime;

    @ApiModelProperty(notes = "account created time", example = "02-12-20200 16:58")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @LastModifiedDate
    @Field("updatedTime")
    @PastOrPresent
    private LocalDateTime updatedTime;

    @ApiModelProperty(notes = "is account persisted", example = "true")
    @Field("persisted")
    private boolean persisted;

    @ApiModelProperty(notes = "account profile photo link", example = "google.com/frugegir38r")
    @Field("profilPhoto")
    private String profilPhotoLink;

    public User(Builder builder) {
        this.setName(builder.name);
        this.setSurname(builder.surname);
        this.setPhone(builder.phone);
        this.setCardNumber(builder.cardNumber);
        this.setPin(builder.pin);
        this.setId(builder.id);
        this.setAge(builder.age);
        this.setGender(builder.gender);
        this.setBirthday(builder.birthday);
        this.setPersisted(builder.persisted);
        this.setProfilPhotoLink(builder.profilPhotoLink);

    }

    @Override
    public boolean isNew() {
        if (!persisted) this.setStatus(Status.CREATED);
        return !persisted;
    }

    @Override
    public Long getId() {
        return id;
    }

    public static Builder build() {
        return new Builder();
    }


    public static class Builder {
        private long id;
        private String name;
        private String surname;
        private int age;
        private String cardNumber;
        private String pin;
        private String phone;
        private LocalDate birthday;
        private String gender;
        private boolean persisted;
        private String profilPhotoLink;

        public Builder name(String name) {
            this.name = name;
            return this;

        }

        public Builder profilPhoto(String profilPhotoLink) {
            this.profilPhotoLink = profilPhotoLink;
            return this;

        }

        public Builder age(int age) {
            this.age = age;
            return this;

        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;

        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;

        }

        public Builder pin(String pin) {
            this.pin = pin;
            return this;

        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;

        }

        public Builder persisted(boolean persisted) {
            this.persisted = persisted;
            return this;

        }


        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;

        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;

        }

        public User build() {
            return new User(this);
        }

    }
}


