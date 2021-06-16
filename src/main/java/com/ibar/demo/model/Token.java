package com.ibar.demo.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TOKEN")
public class Token {
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TokenSequence")
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "userId")
    private long user_id;

    @Column(name = "token")
    private String token;

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @CreationTimestamp
    @Column(name = "createdTime")
    @PastOrPresent
    private LocalDateTime createdTime;

    @Column(name = "isExpired")
    private int expired;

}
