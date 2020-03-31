package com.kspt.app.controllers;

import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.*;
import com.kspt.app.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/endTrip")
    public ApiResult endTrip(@RequestBody Long driverId, @RequestBody int grade) {
        return service.endTrip(driverId, grade);
    }

    @PostMapping("/getFreeTrips")
    public ResponseOrMessage<List<Trip>> getFreeTrips() {
        return service.getFreeTrips();
    }

    @PostMapping("/takeTrip")
    public ApiResult takeTrip(@RequestBody Map<String,Long> id) {
        return service.takeTrip(id);
    }
}
