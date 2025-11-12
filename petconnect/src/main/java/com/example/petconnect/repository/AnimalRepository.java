package com.example.petconnect.repository;

import com.example.petconnect.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {
    List<Animal> findByOngId(String ongId);
}
