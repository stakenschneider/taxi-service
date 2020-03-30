package com.kspt.app.entities.actor;

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
@Table(name = "Admin")
@EqualsAndHashCode(callSuper = true)
public class Admin extends Person {
    public Admin(@NotNull final String firstName,
                 @NotNull final String secondName,
                 final String phoneNumber) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.phoneNumber = phoneNumber;
        this.personType = PersonType.ADMIN;
    }
}
