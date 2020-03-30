package com.kspt.app.controllers;

import com.kspt.app.configuration.Constants.PaymentMethod;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.ApiResult;
import com.kspt.app.models.PassportModel;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.models.TripModelRequest;
import com.kspt.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Masha on 11.03.2020
 */
@RestController
public class ClientController {
    @Autowired
    private ClientService service;

    @PostMapping("{clientId}/setPassportC")
    public ResponseOrMessage<Person> setPassport(@PathVariable final Long clientId,
                                                 @RequestBody PassportModel passport) {
        return service.setPassport(clientId, passport);
    }

    @PostMapping("/getHistoryOfTrips")
    public ResponseOrMessage<List<Trip>> getHistoryOfTrips(@RequestBody Map<String,Long> clientId) {
        return service.getHistoryOfTrips(clientId);
    }

    @PostMapping("{clientId}/requestCar")
    public ApiResult requestCar(@RequestBody TripModelRequest model, @PathVariable Long clientId) {
        return service.requestCar(model, clientId);
    }

    @PostMapping("{tripId}/setGrade")
    public ApiResult setGrade(@PathVariable Long tripId, @RequestBody int grade) {
        return service.setGrade( tripId, grade);
    }

    @PostMapping("{tripId}/changePaymentMethod")
    public ApiResult changePaymentMethod(@PathVariable Long tripId, @RequestBody PaymentMethod newPaymentMethod) {
        return service.changePaymentMethod( tripId,newPaymentMethod);
    }

    @PostMapping("{tripId}/denyTrip")
    public ApiResult denyTrip(@PathVariable Long tripId) {
        return service.denyTrip( tripId);
    }
}
