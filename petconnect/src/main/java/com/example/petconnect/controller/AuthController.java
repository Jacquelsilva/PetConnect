package com.example.petconnect.controller;

import com.example.petconnect.model.User;
import com.example.petconnect.model.Ong;
import com.example.petconnect.service.UserService;
import com.example.petconnect.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OngService ongService;

    // Página de login
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "loginusuario";
    }

    // Processo de login (usuário ou ONG)
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
            // 🔹 Login como usuário
            User validUser = userService.login(user.getUsername(), user.getPassword());
            if (validUser != null) {
                session.setAttribute("usuario", validUser);
                return "redirect:/";
            }

            // 🔹 Login como ONG
            Ong validOng = ongService.login(user.getUsername(), user.getPassword());
            if (validOng != null) {
                session.setAttribute("ong", validOng);
                return "redirect:/";
            }

            // Nenhum deu certo
            model.addAttribute("error", "E-mail, nome de usuário ou senha inválidos!");
            return "loginusuario";

        } catch (IncorrectResultSizeDataAccessException e) {
            model.addAttribute("error", "Erro: há mais de um registro com esse nome de usuário.");
            return "loginusuario";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro interno ao processar o login. Tente novamente.");
            return "loginusuario";
        }
    }

    // Registro de usuário
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "cadastrousuario";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.register(user);
            return "redirect:/auth/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar usuário: " + e.getMessage());
            return "cadastrousuario";
        }
    }

    // Registro de ONG
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
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar ONG: " + e.getMessage());
            return "cadastroongs";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
