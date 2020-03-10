package com.kspt.app.entities;
import com.kspt.app.configuration.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * Created by Masha on 27.02.2020
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Driver")
@EqualsAndHashCode(callSuper = true)
public class Driver extends Person  {
    @JoinColumn(name = "car_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;
    @JoinColumn(name = "trip_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Trip trip;

    public Driver( @NotNull final String firstName,
                   @NotNull final String secondName,
                   @NotNull final Constants.PersonType personType,
                   final int passportCode,
                   final int phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.personType = personType;
        this.passportCode = passportCode;
        this.phoneNumber = phoneNumber;
    }
}
