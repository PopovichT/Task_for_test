package com.example.test_task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {

    @GetMapping("/login")
    public String login() {
        //Here we generate an authentication token
        return "auth-token";
    }

    @GetMapping("/logout")
    public void logout() {
        // invalidate token
    }
}
