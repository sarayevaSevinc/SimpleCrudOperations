package com.ibar.demo.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "IBA_USERS")
public class User implements Serializable, Persistable<UUID> {


    @Id
    @NotNull
    private UUID id;

    @Field("name")
    @NotNull
    private String name;


    @Field("surname")
    private String surname;


    @Field("age")
    private int age;


    @Field("birthday")
    @Past
    private LocalDate birthday;


    @Field("pin")
    @NotNull
    private String pin;


    @Field("cardNumber")
    private String cardNumber;


    @Field("gender")
    private String gender;

    @Field("phone")
    private String phone;

    @Field("status")
    private Status status;


    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @CreatedDate
    @Field("createdTime")
    @PastOrPresent
    private LocalDateTime createdTime;


    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @LastModifiedDate
    @Field("updatedTime")
    @PastOrPresent
    private LocalDateTime updatedTime;

    @Field("persisted")
    private boolean persisted;

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

    }

    @Override
    public boolean isNew() {
        if (!persisted) this.setStatus(Status.CREATED);
        return !persisted;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public static Builder build() {
        return new Builder();
    }


    public static class Builder {
        private UUID id;
        private String name;
        private String surname;
        private int age;
        private String cardNumber;
        private String pin;
        private String phone;
        private LocalDate birthday;
        private String gender;
        private boolean persisted;

        public Builder name(String name) {
            this.name = name;
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


        public Builder id(UUID id) {
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


