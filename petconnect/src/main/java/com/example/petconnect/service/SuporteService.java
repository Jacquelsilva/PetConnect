package com.example.petconnect.service;

import com.example.petconnect.model.Suporte;
import com.example.petconnect.repository.SuporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuporteService {

    @Autowired
    private SuporteRepository suporteRepository;

    public void salvarMensagem(Suporte suporte) {
        suporteRepository.save(suporte);
    }
}
