package com.kspt.app.entities;

import com.kspt.app.configuration.Constants.PersonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Masha on 27.02.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Person extends AbstractEntity{
    @Column(name = "first_name", nullable = false)
    protected String firstName;
    @Column(name = "second_name", nullable = false)
    protected String secondName;
    @Column(name = "person_type", nullable = false)
    protected PersonType personType;
    @Column(name = "passport_code", nullable = false)
    protected int passportCode;
    @Column(name = "phone_number", nullable = false)
    protected int phoneNumber;

    @JoinColumn(name = "credentials_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Credentials credentials;
}
