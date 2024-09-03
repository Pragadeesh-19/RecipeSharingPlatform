package org.pragadeesh.recipesharingplatform.controller;

import org.pragadeesh.recipesharingplatform.dto.UserDto;
import org.pragadeesh.recipesharingplatform.model.JwtRequest;
import org.pragadeesh.recipesharingplatform.model.JwtResponse;
import org.pragadeesh.recipesharingplatform.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto user) {
        return new ResponseEntity<>(authenticationService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest user) {
        return new ResponseEntity<>(authenticationService.login(user), HttpStatus.OK);
    }
}
