package com.example.petconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suporte")
public class SuporteController {

    @GetMapping
    public String mostrarFormulario() {
        return "suporte"; 
    }

    @PostMapping
    public String enviarFormulario(@RequestParam String nome,
                                   @RequestParam String email,
                                   @RequestParam String mensagem,
                                   Model model) {
     
        System.out.println("Mensagem de suporte recebida de: " + nome);

        model.addAttribute("success", "Sua mensagem foi enviada com sucesso!");
        return "suporte"; 
    }
}
