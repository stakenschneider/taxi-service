package com.kspt.app.controllers;

import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.CredentialModel;
import com.kspt.app.models.RegistrationModel;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Masha on 10.03.2020
 */
@RestController
public class PersonController {
    @Autowired
    private PersonService service;

    @PostMapping("/sign-up")
    public ResponseOrMessage<Person> signUp(@RequestBody RegistrationModel model) {
        return service.signUp(model);
    }

    @PostMapping("/sign-in")
    public ResponseOrMessage<Person> signIn(@RequestBody Map<String,String> emailOrUserName) {
        return service.signIn(emailOrUserName);
    }

    @PostMapping("sign-out")
    public boolean signOut() { return service.signOut();}
}
