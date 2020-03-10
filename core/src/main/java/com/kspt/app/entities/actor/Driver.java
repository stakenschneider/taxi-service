package com.kspt.app.entities.actor;

import com.kspt.app.entities.Car;
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
public class Driver extends Person {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name="car_id")
    private Car car;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Trip trip;

    public Driver( @NotNull final String firstName,
                   @NotNull final String secondName,
                   final String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
    }
}
