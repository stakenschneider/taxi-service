package com.kspt.app.entities;

import com.kspt.app.configuration.Constants.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Masha on 27.02.2020
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Client")
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    public Client( @NotNull final String firstName,
                   @NotNull final String secondName,
                   @NotNull final PersonType personType,
                   final int passportCode,
                   final int phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.personType = personType;
        this.passportCode = passportCode;
        this.phoneNumber = phoneNumber;
    }

}
