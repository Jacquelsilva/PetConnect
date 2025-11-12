package com.example.petconnect.service;

import com.example.petconnect.model.Profile;
import com.example.petconnect.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    // 🔹 Listar todos os perfis
    public List<Profile> listarTodos() {
        return profileRepository.findAll();
    }

    // 🔹 Buscar perfil por ID
    public Optional<Profile> buscarPorId(String id) {
        return profileRepository.findById(id);
    }

    // 🔹 Criar ou atualizar perfil
    public Profile salvar(Profile profile) {
        return profileRepository.save(profile);
    }

    // 🔹 Deletar perfil por ID
    public void deletar(String id) {
        profileRepository.deleteById(id);
    }
}
