package com.example.petconnect.repository;

import com.example.petconnect.model.Ong;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends MongoRepository<Ong, String> {
}
