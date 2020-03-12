package com.kspt.app.entities.actor;

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
                   final String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
    }
}
