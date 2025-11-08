package com.example.petconnect.repository;

import com.example.petconnect.model.Suporte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuporteRepository extends MongoRepository<Suporte, String> {
}
