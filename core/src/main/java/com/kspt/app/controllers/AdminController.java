package com.kspt.app.controllers;

import com.kspt.app.configuration.Constants.PersonType;
import com.kspt.app.models.response.ApiResult;
import com.kspt.app.service.AdminService;
import com.kspt.app.service.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Masha on 12.03.2020
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService service;

    @Autowired
    private Generator generator;

    @DeleteMapping("/deletePerson/{personId}/{personType}")
    public ApiResult deletePerson(@PathVariable Long personId, @PathVariable PersonType personType) {
        return service.deletePerson(personId, personType);
    }

    @GetMapping("/generate/{count}")
    public ApiResult generate(@PathVariable int count) {
        return new ApiResult(generator.generateFakeDataForClient(count) +
                generator.generateFakeDataForDriver(count) +
                generator.generateFakeDataForTrip(count));
    }
}
