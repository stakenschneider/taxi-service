package com.kspt.app.entities.actor;

import com.kspt.app.entities.AbstractEntity;
import com.kspt.app.entities.Credentials;
import com.kspt.app.entities.Passport;
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
public abstract class Person extends AbstractEntity {
    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @Column(name = "phone_number")
    protected String phoneNumber;

    @JoinColumn(name = "credentials_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Credentials credentials;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;
}
