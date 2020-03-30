package com.kspt.app.controllers;

import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.*;
import com.kspt.app.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Masha on 12.03.2020
 */
@RestController
public class DriverController {
    @Autowired
    private DriverService service;

    @PostMapping("{driverId}/setCar")
    public ApiResult setCar(@PathVariable Long driverId, @RequestBody CarModel model) {
        return service.setCar(driverId, model);
    }

    @PostMapping("{driverId}/setPassportD")
    public ResponseOrMessage<Person> setPassport(@PathVariable final Long driverId,
                                                 @RequestBody PassportModel passport) {
        return service.setPassport(driverId, passport);
    }

    @PostMapping("{driverId}/endTrip")
    public ApiResult endTrip(@PathVariable Long driverId, @RequestBody int grade) {
        return service.endTrip(driverId, grade);
    }

    @PostMapping("getFreeTrips")
    public ResponseOrMessage<List<Trip>> getFreeTrips() {
        return service.getFreeTrips();
    }

    @PostMapping("{tripId}/{driverId}/takeTrip")
    public ApiResult takeTrip(@PathVariable Long tripId, @PathVariable Long driverId) {
        return service.takeTrip(tripId, driverId);
    }
}
