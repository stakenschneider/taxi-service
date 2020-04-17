package com.kspt.app.controllers;

import com.kspt.app.entities.Trip;
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

    @PostMapping("/setCar")
    public ApiResult setCar(@RequestBody CarModel model) {
        return service.setCar(model);
    }

    @PostMapping("/setPassport")
    public ApiResult setPassport(@RequestBody PassportModel passport) {
        return service.setPassport(passport);
    }

    @PostMapping("/getCurrentTrip")
    public ResponseOrMessage<Trip> getCurrentTrip(@RequestBody Map<String,Long> driverId) {
        return service.getCurrentTrip(driverId);
    }

    @PostMapping("/getHistory")
    public ResponseOrMessage<List<Trip>> getHistory(@RequestBody Map<String,Long> driverId) {
        return service.getHistory(driverId);
    }

    @GetMapping("/getFreeTrips/{driverId}")
    public ResponseOrMessage<List<Trip>> getFreeTrips(@PathVariable Long driverId) {
        return service.getFreeTrips(driverId);
    }

    @PostMapping("/takeTrip")
    public ApiResult takeTrip(@RequestBody Map<String,Long> id) {
        return service.takeTrip(id);
    }

    @PostMapping("/endTrip")
    public ApiResult endTrip(@RequestBody EndTripModel model) {
        return service.endTrip(model);
    }
}
