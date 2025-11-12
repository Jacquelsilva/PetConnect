package com.example.petconnect.controller;

import com.example.petconnect.model.Animal;
import com.example.petconnect.service.AnimalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService animalService;

    // Caminho configurável para salvar as imagens
    @Value("${upload.dir:uploads/}")
    private String uploadDir;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // 🔹 LISTAR TODOS OS ANIMAIS
    @GetMapping
    public String listar(Model model) {
        List<Animal> animais = animalService.listarTodos();
        model.addAttribute("animais", animais);
        return "animais"; // nome da página HTML
    }

    // 🔹 EXIBIR FORMULÁRIO DE CADASTRO
    @GetMapping("/cadastro")
    public String formulario(Model model) {
        model.addAttribute("animal", new Animal());
        return "cadastroanimais"; // página de cadastro
    }

    // 🔹 SALVAR NOVO ANIMAL (com upload de imagem)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Animal animal,
                         @RequestParam("imagem") MultipartFile imagem,
                         RedirectAttributes redirectAttributes) {

        try {
            if (imagem != null && !imagem.isEmpty()) {
                // Cria diretório de uploads se não existir
                Path diretorio = Paths.get(uploadDir);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                // Gera nome único para o arquivo
                String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
                Path caminhoArquivo = diretorio.resolve(nomeArquivo);

                // Salva o arquivo fisicamente
                Files.copy(imagem.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

                // Define a URL pública da imagem
                animal.setImagemUrl("/uploads/" + nomeArquivo);
            }

            // Salva o animal no banco
            animalService.salvar(animal);
            redirectAttributes.addFlashAttribute("success", "Animal cadastrado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erro ao salvar imagem do animal.");
        }

        return "redirect:/animais";
    }
}
