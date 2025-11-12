package com.example.petconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.petconnect.model.Profile;


@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
   
}


