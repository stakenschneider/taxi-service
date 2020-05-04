package com.kspt.app.controllers;

import com.kspt.app.models.person.UserModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

/**
 * Created by Masha on 10.03.2020
 */
@CrossOrigin
@RestController
public class PersonController {

    @RequestMapping("/login")
    public boolean login(@RequestBody UserModel user) {
        return user.getUserName().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
