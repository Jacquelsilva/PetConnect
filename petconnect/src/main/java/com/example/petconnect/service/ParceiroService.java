package com.example.petconnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.petconnect.model.Parceiro;
import com.example.petconnect.repository.ParceiroRepository;

@Service
public class ParceiroService {

    private final ParceiroRepository parceiroRepository;

    public ParceiroService(ParceiroRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    public List<Parceiro> listarTodos() {
        return parceiroRepository.findAll();
    }

    public Optional<Parceiro> buscarPorId(String id) {
        return parceiroRepository.findById(id);
    }

    public Parceiro salvar(Parceiro parceiro) {
        return parceiroRepository.save(parceiro);
    }

    public Parceiro atualizar(String id, Parceiro parceiroAtualizado) {
        Parceiro parceiroExistente = parceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parceiro não encontrado com ID: " + id));

        parceiroExistente.setNome(parceiroAtualizado.getNome());
        parceiroExistente.setEmail(parceiroAtualizado.getEmail());

        return parceiroRepository.save(parceiroExistente);
    }

    public void deletar(String id) {
        parceiroRepository.deleteById(id);
    }
}
