package com.ibar.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "IBA_USERS")
@Validated
@Table(name = "IBA_USERS")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PersonSequence")
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


    @Min(value = 18, message = "Age should not be less than 18")
    @Column(name = "age")
    private int age;

    @Column(name = "profilePicture")
    private String profile_picture_url;


    @Column(name = "birthday")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;


    @Column(name = "pin", unique = true)
    private String pin;

    @Column(name = "password")
    private String password;

    @Column(name = "cardNumber")
    private String card_number;


    @Column(name = "gender")
    private String gender;


    @Column(name = "status")
    private Status status;


    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @CreationTimestamp
    @Column(name = "createdTime")
    @PastOrPresent
    private LocalDateTime createdTime;


    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @UpdateTimestamp
    @Column(name = "updatedTime")
    @PastOrPresent
    private LocalDateTime updatedTime;


    @Column(name = "persisted")
    private boolean persisted;


    public User(Builder builder) {
        this.setName(builder.name);
        this.setSurname(builder.surname);
        this.setCard_number(builder.card_number);
        this.setPin(builder.pin);
        this.setPassword(builder.password);
        this.setId(builder.id);
        this.setAge(builder.age);
        this.setGender(builder.gender);
        this.setBirthday(builder.birthday);
        this.setPersisted(builder.persisted);
        this.setProfile_picture_url(builder.profile_picture_url == null ?
                "There is no profil picture for this user" :
                builder.profile_picture_url);

    }

    public static Builder build() {
        return new Builder();
    }

    @PrePersist
    void preInsert() {
        if (this.status == null) {
            this.status = Status.CREATED;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return pin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static class Builder {
        private long id;
        private String name;
        private String surname;
        private int age;
        private String card_number;
        private String pin;
        private String password;
        private LocalDate birthday;
        private String gender;
        private boolean persisted;
        private String profile_picture_url;

        public Builder name(String name) {
            this.name = name;
            return this;

        }

        public Builder profile_picture_url(String profilePictureUrl) {
            this.profile_picture_url =
                    profilePictureUrl == null ? "There is no profil picture for this user" : profilePictureUrl;
            return this;

        }

        public Builder age(int age) {
            this.age = age;
            return this;

        }
        public Builder password(String password) {
            this.password = password;
            return this;

        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;

        }

        public Builder card_number(String cardNumber) {
            this.card_number = cardNumber;
            return this;

        }

        public Builder pin(String pin) {
            this.pin = pin;
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


