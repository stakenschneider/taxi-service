package com.kspt.app.controllers;

import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.PaymentMethod;
import com.kspt.app.configuration.Constants.Color;
import com.kspt.app.configuration.Constants.CarModels;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.models.SignInModel;
import com.kspt.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Created by Masha on 28.03.2020
 */
@RestController
public class DataController {
    @Autowired
    private PersonService personService;

    @GetMapping("/getPaymentMethods")
    public PaymentMethod[] getPaymentMethod() {
        return PaymentMethod.values();
    }

    @GetMapping("/getCarColor")
    public Color[] getCarColor() {
        return Color.values();
    }

    @GetMapping("/getCarModels")
    public CarModels[] getCarModels() {
        return CarModels.values();
    }

    @GetMapping("/getRate")
    public Rate[] getRate() {
        return Rate.values();
    }

//    TODO input parameter signinmodel rename
    @PostMapping("/getPersonById")
    public ResponseOrMessage<Person> getPersonById(@RequestBody SignInModel model) {
        return personService.getPersonById(model);
    }
}
