package com.kspt.app.controllers;

import com.kspt.app.models.*;
import com.kspt.app.service.AdminService;
import com.kspt.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//
///**
// * Created by Masha on 12.03.2020
// */
//@RestController
//public class AdminController {
//    @Autowired
//    private AdminService service;
//
////    TODO Polymorphic Queries
//    @DeleteMapping("{driverId}/deleteDriver")
//    public ApiResult deleteDriver(@PathVariable Long driverId) {
//        return service.deleteDriver( driverId);
//    }
//
//    @DeleteMapping("{clientId}/deleteClient")
//    public ApiResult deleteClient( @PathVariable Long clientId) {
//        return service.deleteClient( clientId);
//    }
//}

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }
}