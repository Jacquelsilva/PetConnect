package com.example.petconnect.controller;

import com.example.petconnect.model.User;
import com.example.petconnect.model.Ong;
import com.example.petconnect.service.UserService;
import com.example.petconnect.service.OngService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final OngService ongService;

    public AuthController(UserService userService, OngService ongService) {
        this.userService = userService;
        this.ongService = ongService;
    }

    
    @GetMapping("/login")
    public String showLoginPage() {
        return "loginusuario";
    }

    @PostMapping("/login")
public String login(@ModelAttribute User user, Model model) {
    User validUser = userService.login(user.getUsername(), user.getPassword());

    if (validUser != null) {
        model.addAttribute("user", validUser);
        return "index"; 
    }

    model.addAttribute("error", "Usuário ou senha inválidos!");
    return "loginusuario";
}


   
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "cadastrousuario"; 
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.register(user);
            
            return "redirect:/auth/login?success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "cadastrousuario";
        }
    }


    @GetMapping("/register/ongs")
    public String showOngRegisterPage(Model model) {
        model.addAttribute("ong", new Ong());
        return "cadastroongs"; 
    }

    @PostMapping("/register/ongs")
    public String registerOng(@ModelAttribute("ong") Ong ong, Model model) {
        try {
            ongService.register(ong);
         
            return "redirect:/auth/login?successOng";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "cadastroongs";
        }
    }

   
    @GetMapping("/logout")
    public String logout() {
      
        return "redirect:/";
    }
}
