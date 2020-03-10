package com.kspt.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public final class HelloController{

    public HelloController() {

    }

    @GetMapping("/getHello")
    public String getHello() {
        return "Helloooo";
    }
}
