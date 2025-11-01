package com.example.petconnect.controller;


import com.example.petconnect.model.User;
import com.example.petconnect.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User validUser = service.login(user.getUsername(), user.getPassword());
        return validUser != null ? "Login realizado com sucesso!" : "Usuário ou senha inválidos!";
    }
}
 
    
