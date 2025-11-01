package com.example.petconnect.repository;

import com.example.petconnect.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Object> {
    Optional<User> findByUsername(String username);
}
