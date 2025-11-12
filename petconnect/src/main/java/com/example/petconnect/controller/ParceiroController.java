package com.example.petconnect.controller;

import com.example.petconnect.model.Parceiro;
import com.example.petconnect.service.ParceiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parceiros")
@CrossOrigin(origins = "*")
public class ParceiroController {

    private final ParceiroService parceiroService;

    public ParceiroController(ParceiroService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @GetMapping
    public ResponseEntity<List<Parceiro>> listarTodos() {
        return ResponseEntity.ok(parceiroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parceiro> buscarPorId(@PathVariable String id) {
        return parceiroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parceiro> criar(@RequestBody Parceiro parceiro) {
        return ResponseEntity.ok(parceiroService.salvar(parceiro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiro> atualizar(@PathVariable String id, @RequestBody Parceiro parceiroAtualizado) {
        return parceiroService.atualizar(id, parceiroAtualizado)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if (parceiroService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
