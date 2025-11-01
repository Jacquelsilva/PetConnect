package com.example.petconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.petconnect.model.Parceiro;

public interface ParceiroRepository extends MongoRepository<Parceiro, String> {
}
