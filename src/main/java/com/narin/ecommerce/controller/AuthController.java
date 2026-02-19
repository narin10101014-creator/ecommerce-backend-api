package com.narin.ecommerce.controller;

import com.narin.ecommerce.dto.LoginRequest;
import com.narin.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return authService.login(req.getUsername(), req.getPassword());
    }
}
