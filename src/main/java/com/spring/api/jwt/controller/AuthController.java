package com.spring.api.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.jwt.model.LoginUserRequest;
import com.spring.api.jwt.model.TokenResponse;
import com.spring.api.jwt.model.WebResponse;
import com.spring.api.jwt.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(
        path = "/api/auth/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        
        return WebResponse.<TokenResponse>builder()
                                        .status(true)
                                        .messages("Login success")
                                        .data(tokenResponse)
                                        .build();
    }


}
