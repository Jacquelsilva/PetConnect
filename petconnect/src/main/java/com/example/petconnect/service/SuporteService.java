package com.example.petconnect.service;

import com.example.petconnect.model.Suporte;
import com.example.petconnect.repository.SuporteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuporteService {

    private final SuporteRepository repository;

    public SuporteService(SuporteRepository repository) {
        this.repository = repository;
    }

    public Suporte salvarMensagem(Suporte suporte) {
        return repository.save(suporte);
    }

    public List<Suporte> listarMensagens() {
        return repository.findAll();
    }
}
