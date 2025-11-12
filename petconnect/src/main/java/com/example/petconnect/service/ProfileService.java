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

    
    public List<Profile> listarTodos() {
        return profileRepository.findAll();
    }

    public Optional<Profile> buscarPorId(String id) {
        return profileRepository.findById(id);
    }


    public Profile salvar(Profile profile) {
        return profileRepository.save(profile);
    }


    public void deletar(String id) {
        profileRepository.deleteById(id);
    }
}
