package com.example.petconnect.service;

import com.example.petconnect.model.Ong;
import com.example.petconnect.repository.OngRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OngService {

    private final OngRepository repository;

    public OngService(OngRepository repository) {
        this.repository = repository;
    }

    public Ong register(Ong ong) {
        return repository.save(ong);
    }

    public List<Ong> listarOngs() {
        return repository.findAll();
    }

    public Ong getOngById(String id) {
        return repository.findById(id).orElse(null);
    }

   
    public Ong login(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha);
    }
}
