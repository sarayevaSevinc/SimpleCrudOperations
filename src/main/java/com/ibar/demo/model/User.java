package com.ibar.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "IBA_USERS")
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class User implements Serializable {


    //    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PersonSequence")
//    @Id
////    @GeneratedValue(generator = "uuid2")
////    @GenericGenerator(name = "uuid2", strategy = "uuid2")
////    @Column(name = "uuid", columnDefinition = "BINARY(16)")
////    @Id
////    @Type(type="uuid2-char")
////    private UUID id;
//    private int id;
//
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
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

    @Column(name = "updatedTime", nullable = true)
    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime updatedTime;

    @PrePersist
    void preInsert() {
        if (this.status == null)
            this.status = Status.CREATED;
    }


}



