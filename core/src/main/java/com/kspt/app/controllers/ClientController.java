package com.kspt.app.controllers;

import com.kspt.app.models.ApiResult;
import com.kspt.app.models.PassportModel;
import com.kspt.app.models.PersonResponse;
import com.kspt.app.models.TripModelRequest;
import com.kspt.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Masha on 11.03.2020
 */
@RestController
public class ClientController {
    @Autowired
    private ClientService service;

    @PostMapping("{clientId}/setPassportC")
    public PersonResponse setPassport(@PathVariable final Long clientId,
                                      @RequestBody PassportModel passport) {
        return service.setPassport(clientId, passport);
    }

    @PostMapping("{clientId}/requestCar")
    public ApiResult requestCar(@RequestBody TripModelRequest model, @PathVariable Long clientId) {
        return service.requestCar(model, clientId);
    }

    @PostMapping("{tripId}/{rate}/setRate")
    public ApiResult setRate(@PathVariable Long tripId, @PathVariable int rate) {
        return service.setRate( tripId, rate);
    }

    @PostMapping("{tripId}/{denyTrip")
    public ApiResult denyTrip(@PathVariable Long tripId) {
        return service.denyTrip( tripId);
    }
}
