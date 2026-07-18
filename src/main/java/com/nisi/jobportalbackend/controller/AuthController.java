package com.nisi.jobportalbackend.controller;

import com.nisi.jobportalbackend.dto.LoginRequest;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.service.JwtService;
import com.nisi.jobportalbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth")
@RestController
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public ResponseEntity<User> authRegisterUser(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerUser(user));
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword())
        );

        String token = jwtService.generateToken(loginRequest.getEmail());
        return ResponseEntity.ok(token);  //sends token back to client
    }
}
