package com.security.backendSecurity.controller;

import com.security.backendSecurity.model.loginUsers;
import com.security.backendSecurity.model.registerUser;
import com.security.backendSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestBody registerUser request) {
        return service.register(request);
    }

     @PostMapping("/login")
     public String login(@RequestBody loginUsers request){
        return service.verify(request);
     }
}
