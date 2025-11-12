package com.example.petconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.petconnect.model.Parceiro;

@Repository
public interface ParceiroRepository extends MongoRepository<Parceiro, String> {
   
}
