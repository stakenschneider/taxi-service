package com.kspt.app.entities.actor;

import com.kspt.app.configuration.Constants.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
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

    @Column(name = "rating", nullable = false)
    private Double rating;

    public Client( @NotNull final String firstName,
                   @NotNull final String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.rating = 5.0;
        this.personType = PersonType.CLIENT;
    }
}
