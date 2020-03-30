package com.kspt.app.controllers;

import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.PaymentMethod;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Created by Masha on 28.03.2020
 */
@RestController
public class DataController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/getPaymentMethods")
    public PaymentMethod[] getPaymentMethod() {
        return PaymentMethod.values();
    }

    @GetMapping("/getRate")
    public Rate[] getRate() {
        return Rate.values();
    }

    @PostMapping("/getClientById")
    public ResponseOrMessage<Person> getClientById(@RequestBody Map<String,Long> clientId) {
        return clientService.getClientById(clientId);
    }
}
