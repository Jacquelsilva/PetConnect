package com.example.petconnect.controller;

import com.example.petconnect.model.Ong;
import com.example.petconnect.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService ongService;

    @GetMapping
    public String listarOngs(Model model) {
        model.addAttribute("ongs", ongService.listarOngs());
        return "ongs";
    }

  
    @GetMapping("/{id}")
    public String mostrarPerfilOng(@PathVariable("id") String id, Model model) {
        Ong ong = ongService.getOngById(id);

        if (ong == null) {
            return "redirect:/ongs?notfound";
        }

        model.addAttribute("ong", ong);
        return "perfilong";
    }

  
    @GetMapping("/editar/{id}")
    public String editarPerfil(@PathVariable("id") String id, Model model, HttpSession session) {
        Ong ong = ongService.getOngById(id);

        if (ong == null) {
            return "redirect:/ongs?notfound";
        }

       
        Ong ongLogada = (Ong) session.getAttribute("ong"); 

        if (ongLogada == null || !ongLogada.getId().equals(id)) {
            return "redirect:/ongs/" + id + "?semPermissao";
        }

        model.addAttribute("ong", ong);
        return "editarong";
    }

  
    @PostMapping("/editar")
    public String salvarEdicao(@ModelAttribute("ong") Ong ongAtualizada, HttpSession session) {
        Ong ongLogada = (Ong) session.getAttribute("ong");

        if (ongLogada == null || !ongLogada.getId().equals(ongAtualizada.getId())) {
            return "redirect:/ongs/" + ongAtualizada.getId() + "?semPermissao";
        }

      
        ongLogada.setNomeOng(ongAtualizada.getNomeOng());
        ongLogada.setDescricao(ongAtualizada.getDescricao());
        ongLogada.setResponsavel(ongAtualizada.getResponsavel());
        ongLogada.setTelefone(ongAtualizada.getTelefone());
        ongLogada.setEndereco(ongAtualizada.getEndereco());
        ongLogada.setRedeSocial(ongAtualizada.getRedeSocial());
        ongLogada.setEmail(ongAtualizada.getEmail());

        ongService.register(ongLogada);
        session.setAttribute("ong", ongLogada); 

        return "redirect:/ongs/" + ongLogada.getId() + "?editSuccess";
    }
}
