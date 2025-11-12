package com.example.petconnect.controller;

import com.example.petconnect.model.User;
import com.example.petconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/perfil")
public class ProfileController {

    @Autowired
    private UserService userService;

    // Exibe o perfil do usuário
    @GetMapping("/{id}")
    public String showProfile(@PathVariable String id, Model model) {
        User usuario = userService.getUserById(id);

        if (usuario == null) {
            model.addAttribute("error", "Usuário não encontrado!");
            return "redirect:/auth/login";
        }

        // 🔹 usa "usuario" para coincidir com o nome no HTML (perfil.html)
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    // Atualiza o perfil do usuário
    @PostMapping("/{id}/editar")
    public String updateProfile(@PathVariable String id,
                                @ModelAttribute User updatedUser,
                                Model model) {
        User usuario = userService.updateProfile(id, updatedUser);

        if (usuario == null) {
            model.addAttribute("error", "Erro ao atualizar perfil!");
            return "perfil";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("success", "Perfil atualizado com sucesso!");
        return "perfil";
    }
}
