package com.example.petconnect.service;

import com.example.petconnect.model.Parceiro;
import com.example.petconnect.repository.ParceiroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<Parceiro> atualizar(String id, Parceiro parceiroAtualizado) {
        return parceiroRepository.findById(id).map(parceiroExistente -> {
            parceiroExistente.setNome(parceiroAtualizado.getNome());
            parceiroExistente.setEmail(parceiroAtualizado.getEmail());
            parceiroExistente.setTelefone(parceiroAtualizado.getTelefone());
            parceiroExistente.setEndereco(parceiroAtualizado.getEndereco());
            parceiroExistente.setDescricao(parceiroAtualizado.getDescricao());
            return parceiroRepository.save(parceiroExistente);
        });
    }

    public boolean deletar(String id) {
        return parceiroRepository.findById(id).map(parceiro -> {
            parceiroRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
