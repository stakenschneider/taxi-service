package com.kspt.app.controllers;

import com.kspt.app.models.CredentialModel;
import com.kspt.app.models.PersonResponse;
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

    @PostMapping("/signUp")
    public PersonResponse signUp(@RequestBody RegistrationModel model) {
        return service.signUp(model);
    }

    @PostMapping("/signIn")
    public PersonResponse signIn(@RequestBody CredentialModel credential) {
        return service.signIn(credential);
    }

    @PostMapping("signOut")
    public boolean signOut() { return service.signOut();}
}
