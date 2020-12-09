package com.ibar.demo.model;

import com.ibar.demo.annotation.PhoneNumberConstraint;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "phones")
public class PhoneNumber implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PhoneSequence")
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @PhoneNumberConstraint
    @Column(name = "phone")
    private String phone;


}
