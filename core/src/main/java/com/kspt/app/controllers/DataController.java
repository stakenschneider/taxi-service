package com.kspt.app.controllers;

import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.PaymentMethod;
import com.kspt.app.configuration.Constants.Color;
import com.kspt.app.configuration.Constants.CarModels;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.person.IdAndPersonTypeModel;
import com.kspt.app.service.DataService;
import com.kspt.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Masha on 28.03.2020
 */
@RestController
public class DataController {
    @Autowired
    private PersonService personService;

    @Autowired
    private DataService dataService;

    @GetMapping("/getPaymentMethods")
    public PaymentMethod[] getPaymentMethod() {
        return dataService.getPM();
    }

    @GetMapping("/getCarColor")
    public Color[] getCarColor() {
        return dataService.getCarColors();
    }

    @GetMapping("/getCarModels")
    public CarModels[] getCarModels() {
        return dataService.getCarModels();
    }

    @GetMapping("/getRate")
    public Rate[] getRate() {
        return dataService.getRates();
    }

    @PostMapping("/getPersonById")
    public ResponseOrMessage<Person> getPersonById(@RequestBody IdAndPersonTypeModel model) {
        return personService.getPersonById(model);
    }

    @GetMapping("/getTripById/{tripId}")
    public ResponseOrMessage<Trip> generate(@PathVariable Long tripId) {
        return dataService.getTripById(tripId);
    }
}
