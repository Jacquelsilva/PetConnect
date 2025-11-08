package com.example.petconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping({"/", "/index"})
public String index() {
    return "index";
}


    @GetMapping("/animais")
    public String animais() {
        return "animais";
    }

    @GetMapping("/informacoes")
    public String informacoes() {
        return "informacoes";
    }

    @GetMapping("/ongs")
    public String ongs() {
        return "ongs";
    }
}
