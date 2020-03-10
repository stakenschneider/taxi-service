package com.kspt.app.entities;
import com.kspt.app.configuration.Constants.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Masha on 27.02.2020
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Trip")
@EqualsAndHashCode(callSuper = true)
public class Trip extends AbstractEntity{
    @Column(name = "trip_id", nullable = false)
    private Long tripId;
//    @JoinColumn(name = "credentials_id")
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Client client;
//    @Column(name = "driver", nullable = false)
//    private Driver driver;
    @Column(name = "status", nullable = false)
    private Status status;
//    @Column(name = "start_address", nullable = false)
//    private Address startAddress;
//    @Column(name = "finish_address", nullable = false)
//    private Address finishAddress;
    @Column(name = "rate", nullable = false)
    private int rate;
    @Column(name = "price", nullable = false)
    private Double price;

}
