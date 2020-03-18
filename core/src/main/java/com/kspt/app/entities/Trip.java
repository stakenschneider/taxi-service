package com.kspt.app.entities;

import com.kspt.app.configuration.Constants.PaymentMethod;
import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.Status;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Masha on 27.02.2020
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Trip")
@EqualsAndHashCode(callSuper = true)
public class Trip extends AbstractEntity{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name="client_id")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name="driver_id")
    private Driver driver;

    @Column(name = "status", nullable = false)
    private Status status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name="start_address_id")
    private Address startAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name="finish_address_id")
    private Address finishAddress;

    @Column(name = "date_of_creation", nullable = false)
    private Date dateOfCreation;

    @Column(name = "date_of_completion")
    private Date dateOfCompletion;

    @Column(name = "rating")
    private int rating;

    @Column(name = "rate", nullable = false)
    private Rate tripRate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
}
