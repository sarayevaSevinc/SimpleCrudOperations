package com.ibar.demo.model;

import com.ibar.demo.annotation.GenderConstraint;
import com.ibar.demo.annotation.NameConstraint;
import com.ibar.demo.annotation.PinConstraint;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;
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

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PersonSequence")
    @Column(name = "id")
    @Id
    private long id;

    @NameConstraint
    @Column(name = "name")
    private String name;

    @NameConstraint
    @Column(name = "surname")
    private String surname;


    @Min(value = 18, message = "Age should not be less than 18")
    @Column(name = "age")
    private int age;




    @Column(name = "birthday")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;


    @PinConstraint
    @Column(name = "pin")
    private String pin;


    @Column(name = "cardNumber")
    private String cardNumber;



    @Column(name = "gender")
    private String gender;

    @GenderConstraint
    @Column( name = "status")
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


    @PrePersist
    void preInsert() {
        if (this.status == null)
            this.status = Status.CREATED;
    }

    public User(Builder builder) {
        this.setName(builder.name);
        this.setSurname(builder.surname);
        this.setCardNumber(builder.cardNumber);
        this.setPin(builder.pin);
        this.setId(builder.id);
        this.setAge(builder.age);
        this.setGender(builder.gender);
        this.setBirthday(builder.birthday);
        this.setPersisted(builder.persisted);

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


