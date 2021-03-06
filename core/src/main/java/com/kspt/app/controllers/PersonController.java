package com.kspt.app.controllers;

import com.kspt.app.models.person.RegistrationModel;
import com.kspt.app.models.person.SignInResponse;
import com.kspt.app.models.response.ResponseOrMessage;
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
    public ResponseOrMessage<Boolean> signUp(@RequestBody RegistrationModel model) {
        return service.signUp(model);
    }

    @PostMapping("/sign-in")
    public ResponseOrMessage<SignInResponse> signIn(@RequestBody Map<String, String> emailOrUserName) {
        return service.signIn(emailOrUserName);
    }

    // TODO implement or delete
//    @PostMapping("/sign-out")
//    public boolean signOut() {
//        return service.signOut();
//    }
}
