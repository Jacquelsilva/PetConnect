package com.example.petconnect.controller;

import com.example.petconnect.model.Suporte;
import com.example.petconnect.service.SuporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SuporteController {

    @Autowired
    private SuporteService suporteService;

    @PostMapping("/suporte")
    public String enviarMensagem(@ModelAttribute Suporte suporte, Model model) {
        suporteService.salvarMensagem(suporte);
        model.addAttribute("success", "Mensagem enviada com sucesso! Entraremos em contato em breve 😊");
        return "informacoes"; 
    }

    @GetMapping("/informacoes")
    public String mostrarPagina() {
        return "informacoes"; 
    }
}
