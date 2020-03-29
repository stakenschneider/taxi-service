package com.kspt.app.controllers;

import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.PaymentMethod;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Masha on 28.03.2020
 */
@RestController
public class DataController {

    @GetMapping("/getPaymentMethods")
    public PaymentMethod[] getPaymentMethod() {
        return PaymentMethod.values();
    }

    @GetMapping("/getRate")
    public Rate[] getRate() {
        return Rate.values();
    }
}
