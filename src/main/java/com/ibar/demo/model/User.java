package com.ibar.demo.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "IBA_USERS")
public class User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Column( name = "id")
    @ApiModelProperty(notes = "user id", example = "123")
    @Id
    private long id;

    @ApiModelProperty(notes = "user name", example = "Eli")
    @Column( name = "name")
    private String name;

    @ApiModelProperty(notes = "user surname", example = "Eliyev")
    @Column( name = "surname")
    private String surname;


    @ApiModelProperty(notes = "user age", example = "20")
    @Column( name = "age")
    private int age;



    @ApiModelProperty(notes = "user birthday", example = "27-07-2000")
    @Column( name = "birthday")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;

    @ApiModelProperty(notes = "user pin", example = "AZE24347855")
    @Column( name = "pin")
    private String pin;

    @ApiModelProperty(notes = "user card number", example = "1234567891234567")
    @Column( name = "cardNumber")
    private String cardNumber;


    @ApiModelProperty(notes = "user gender", example = "WOMAN")
    @Column( name = "gender")
    private String gender;

    @ApiModelProperty(notes = "user phone number", example = "+994501234567")
    @Column( name = "phone")
    private String phone;

    @ApiModelProperty(notes = "user account status", example = "CREATED")
    @Column( name = "status")
    private Status status;


    @ApiModelProperty(notes = "account created time", example = "02-12-2020 16:57")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @CreationTimestamp
    @Column( name = "createdTime")
    @PastOrPresent
    private LocalDateTime createdTime;

    @ApiModelProperty(notes = "account created time", example = "02-12-2020 16:58")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @UpdateTimestamp
    @Column( name = "updatedTime")
    @PastOrPresent
    private LocalDateTime updatedTime;

    @ApiModelProperty(notes = "is account persisted", example = "true")
    @Column( name = "persisted")
    private boolean persisted;

    @ApiModelProperty(notes = "account profile photo link", example = "google.com/feoifegfkrnk")
    @Column( name = "profilPhoto")
    private String profilPhotoLink;

    @PrePersist
    void preInsert() {
        if (this.status == null)
            this.status = Status.CREATED;
    }
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

//    @Override
//    public boolean isNew() {
//        if (!persisted) {
//            this.setStatus(Status.CREATED);
//        }
//        return !persisted;
//    }
//
//    @Override
//    public Long getId() {
//        return id;
//    }

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


