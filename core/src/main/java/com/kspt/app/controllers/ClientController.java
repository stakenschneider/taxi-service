package com.kspt.app.controllers;

import com.kspt.app.entities.Trip;
import com.kspt.app.models.response.ApiResult;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.trip.SetGradeModel;
import com.kspt.app.models.trip.TripModelRequest;
import com.kspt.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * Created by Masha on 11.03.2020
 */
@RestController
public class ClientController {
    @Autowired
    private ClientService service;

    @PostMapping("/getActiveTrip")
    public ResponseOrMessage<Trip> getActiveTrip(@RequestBody Map<String,Long> clientId) {
        return service.getActiveTrip(clientId);
    }

    @PostMapping("/requestCar")
    public ApiResult requestCar(@RequestBody TripModelRequest model) {
        return service.requestCar(model);
    }

    @PostMapping("/denyTrip")
    public ApiResult denyTrip(@RequestBody Map<String,Long> clientId) {
        return service.denyTrip(clientId);
    }

    @PostMapping("/setGrade")
    public ApiResult setGrade(@RequestBody SetGradeModel model) {
        return service.setGrade(model);
    }
}
