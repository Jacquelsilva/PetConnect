package com.example.petconnect.service;

import com.example.petconnect.model.Animal;
import com.example.petconnect.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public Animal salvar(Animal animal) {
        return repository.save(animal);
    }

    public List<Animal> listarTodos() {
        return repository.findAll();
    }

    public List<Animal> listarPorOng(String ongId) {
        return repository.findByOngId(ongId);
    }
}
