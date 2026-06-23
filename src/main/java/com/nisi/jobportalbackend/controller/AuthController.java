package com.nisi.jobportalbackend.controller;

import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth")
@RestController
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("register")
    public ResponseEntity<User> authRegisterUser(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerUser(user));
    }

}
