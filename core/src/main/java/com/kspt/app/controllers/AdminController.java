package com.kspt.app.controllers;

import com.kspt.app.models.*;
import com.kspt.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Masha on 12.03.2020
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService service;

//    TODO Polymorphic Queries
    @DeleteMapping("{driverId}/deleteDriver")
    public ApiResult deleteDriver(@PathVariable Long driverId) {
        return service.deleteDriver( driverId);
    }

    @DeleteMapping("{clientId}/deleteClient")
    public ApiResult deleteClient( @PathVariable Long clientId) {
        return service.deleteClient( clientId);
    }
}
