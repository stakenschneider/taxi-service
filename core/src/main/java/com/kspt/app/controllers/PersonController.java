package com.kspt.app.controllers;

import com.kspt.app.models.CredentialModel;
import com.kspt.app.models.RegistrationModel;
import com.kspt.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Masha on 10.03.2020
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationModel registrationModel) {
        service.register(registrationModel);
    }

    @PostMapping("/login")
    public void login(@RequestBody CredentialModel credentialModel) {

    }


}
