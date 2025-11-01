package com.example.petconnect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petconnect.model.Parceiro;
import com.example.petconnect.service.ParceiroService;

@RestController
@RequestMapping("/parceiro")
public class ParceiroController {

    @Autowired
    private ParceiroService parceiroService;

    @GetMapping
    public List<Parceiro> listarTodos() {
        return parceiroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parceiro> buscarPorId(@PathVariable String id) {
        Optional<Parceiro> parceiro = parceiroService.buscarPorId(id);
        return parceiro.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parceiro> criar(@RequestBody Parceiro parceiro) {
        Parceiro novoParceiro = parceiroService.salvar(parceiro);
        return ResponseEntity.ok(novoParceiro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiro> atualizar(@PathVariable String id, @RequestBody Parceiro parceiro) {
        Parceiro parceiroAtualizado = parceiroService.atualizar(id, parceiro);
        return ResponseEntity.ok(parceiroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        parceiroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
