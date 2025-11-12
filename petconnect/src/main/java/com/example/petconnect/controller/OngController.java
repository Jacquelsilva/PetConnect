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

    // Lista todas as ONGs
    @GetMapping
    public String listarOngs(Model model) {
        model.addAttribute("ongs", ongService.listarOngs());
        return "ongs";
    }

    // Exibe o perfil de uma ONG específica
    @GetMapping("/{id}")
    public String mostrarPerfilOng(@PathVariable("id") String id, Model model) {
        Ong ong = ongService.getOngById(id);

        if (ong == null) {
            return "redirect:/ongs?notfound";
        }

        model.addAttribute("ong", ong);
        return "perfilong";
    }

    // Exibe o formulário de edição
    @GetMapping("/editar/{id}")
    public String editarPerfil(@PathVariable("id") String id, Model model, HttpSession session) {
        Ong ong = ongService.getOngById(id);

        if (ong == null) {
            return "redirect:/ongs?notfound";
        }

        // Verifica se a ONG logada é a mesma que está tentando editar
        Ong ongLogada = (Ong) session.getAttribute("ong"); // 🔧 Corrigido: agora pega a ONG logada correta

        if (ongLogada == null || !ongLogada.getId().equals(id)) {
            return "redirect:/ongs/" + id + "?semPermissao";
        }

        model.addAttribute("ong", ong);
        return "editarOng";
    }

    // Salva as alterações da ONG
    @PostMapping("/editar")
    public String salvarEdicao(@ModelAttribute("ong") Ong ongAtualizada, HttpSession session) {
        Ong ongLogada = (Ong) session.getAttribute("ong");

        if (ongLogada == null || !ongLogada.getId().equals(ongAtualizada.getId())) {
            return "redirect:/ongs/" + ongAtualizada.getId() + "?semPermissao";
        }

        // Atualiza os dados
        ongLogada.setNomeOng(ongAtualizada.getNomeOng());
        ongLogada.setDescricao(ongAtualizada.getDescricao());
        ongLogada.setResponsavel(ongAtualizada.getResponsavel());
        ongLogada.setTelefone(ongAtualizada.getTelefone());
        ongLogada.setEndereco(ongAtualizada.getEndereco());
        ongLogada.setRedeSocial(ongAtualizada.getRedeSocial());
        ongLogada.setEmail(ongAtualizada.getEmail());

        // Salva no banco
        ongService.register(ongLogada);
        session.setAttribute("ong", ongLogada); // 🔧 Atualiza a sessão

        return "redirect:/ongs/" + ongLogada.getId() + "?editSuccess";
    }
}
