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

    @Value("${upload.dir:uploads/}")
    private String uploadDir;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Animal> animais = animalService.listarTodos();
        model.addAttribute("animais", animais);
        return "animais";
    }

   
    @GetMapping("/cadastro")
    public String formulario(Model model) {
        model.addAttribute("animal", new Animal());
        return "cadastroanimais";
    }

  
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Animal animal,
                         @RequestParam("imagem") MultipartFile imagem,
                         RedirectAttributes redirectAttributes) {

        try {
            if (imagem != null && !imagem.isEmpty()) {

                Path diretorio = Paths.get(uploadDir);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
                Path caminhoArquivo = diretorio.resolve(nomeArquivo);
                Files.copy(imagem.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

                animal.setImagemUrl("/uploads/" + nomeArquivo);
            }

            animalService.salvar(animal);
            redirectAttributes.addFlashAttribute("success", "Animal cadastrado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erro ao salvar imagem do animal.");
        }

        return "redirect:/animais";
    }

    
   @PostMapping("/excluir/{id}")
public String excluir(@PathVariable String id, RedirectAttributes redirectAttributes) {
    try {
        animalService.deletar(id); 
        redirectAttributes.addFlashAttribute("success", "Animal excluído com sucesso!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Erro ao excluir animal.");
    }
    return "redirect:/animais";
}


    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable String id, Model model) {

        Animal animal = animalService.buscarPorId(id);

        if (animal == null) {
            return "redirect:/animais";
        }

        model.addAttribute("animal", animal);
        return "editaranimal"; 
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable String id,
                            @ModelAttribute Animal atualizado,
                            @RequestParam(value = "imagem", required = false) MultipartFile novaImagem,
                            RedirectAttributes redirectAttributes) {

        try {
            Animal existente = animalService.buscarPorId(id);

            if (existente == null) {
                redirectAttributes.addFlashAttribute("error", "Animal não encontrado.");
                return "redirect:/animais";
            }

            existente.setNome(atualizado.getNome());
            existente.setIdade(atualizado.getIdade());
            existente.setEspecie(atualizado.getEspecie());
            existente.setDescricao(atualizado.getDescricao());

            if (novaImagem != null && !novaImagem.isEmpty()) {

                Path diretorio = Paths.get(uploadDir);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                String nomeArquivo = UUID.randomUUID() + "_" + novaImagem.getOriginalFilename();
                Path caminhoArquivo = diretorio.resolve(nomeArquivo);

                Files.copy(novaImagem.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

                existente.setImagemUrl("/uploads/" + nomeArquivo);
            }

            animalService.salvar(existente);
            redirectAttributes.addFlashAttribute("success", "Animal atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar animal.");
        }

        return "redirect:/animais";
    }
}
