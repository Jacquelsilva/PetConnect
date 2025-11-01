package com.example.petconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.petconnect.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
