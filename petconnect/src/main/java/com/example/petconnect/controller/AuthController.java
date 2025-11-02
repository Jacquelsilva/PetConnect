package com.example.petconnect.controller;

import com.example.petconnect.model.User;
import com.example.petconnect.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "loginusuario"; // login.html em /resources/templates/
    }

    
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        User validUser = service.login(user.getUsername(), user.getPassword());

        if (validUser != null) {
            return "redirect:/dashboard"; 
        }

        model.addAttribute("error", "Usuário ou senha inválidos!");
        return "loginusuario"; 
    }

   @PostMapping("/register")
public String register(@ModelAttribute User user, Model model) {
    try {
        service.register(user);
        return "redirect:/auth/login?success";
    } catch (RuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "cadastrousuario"; 
    }
}

}
