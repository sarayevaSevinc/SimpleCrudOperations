package com.ibar.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
