package com.ibar.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "IBA_USERS")
@AllArgsConstructor
@NoArgsConstructor
//@Builder(builderClassName = "Builder", toBuilder = true)
public class User implements Serializable {


    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PersonSequence")
    @Id
    private int id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @NonNull
    @Column(name = "pin")
    private String pin;

    @NonNull
    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "gender")
    private String gender;

    @NonNull
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;


    @Column(name = "createdTime", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime createdTime;

    @Column(name = "updatedTime",)
    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime updatedTime;

    @PrePersist
    void preInsert() {
        if (this.status == null)
            this.status = Status.CREATED;
    }

    public User(UserBuilder userBuilder) {
        this.setName(userBuilder.name);
        this.setSurname(userBuilder.surname);
        this.setPhone(userBuilder.phone);
        this.setCardNumber(userBuilder.cardNumber);
        this.setPin(userBuilder.pin);
    }

    public static class UserBuilder {
        private int id;
        private final String name;
        private final String surname;
        private int age;
        private final String cardNumber;
        private final String pin;
        private final String phone;
        private LocalDate birthday;
        private String gender;


        public UserBuilder(String name, String surname, String cardNumber, String pin, String phone) {
            this.name = name;
            this.surname = surname;
            this.cardNumber = cardNumber;
            this.pin = pin;
            this.phone = phone;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;

        }

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;

        }

        public UserBuilder setGender(String gender) {
            this.gender = gender;
            return this;

        }

        public User build() {
            return new User(this);
        }
    }

}



