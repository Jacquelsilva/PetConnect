package com.example.petconnect.service;

import com.example.petconnect.model.User;
import com.example.petconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return null; 
        }
        return userRepository.save(user);
    }

    
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

  
    public User updateProfile(String id, User updatedUser) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setNomeCompleto(updatedUser.getNomeCompleto());
            existingUser.setTelefone(updatedUser.getTelefone());
            existingUser.setEndereco(updatedUser.getEndereco());
            return userRepository.save(existingUser);
        }
        return null;
    }
}
