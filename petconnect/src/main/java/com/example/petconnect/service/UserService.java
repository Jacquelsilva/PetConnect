package com.example.petconnect.service;

import com.example.petconnect.model.User;
import com.example.petconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


   

    public User register(User user) {
  
        if (userRepository.findFirstByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com este e-mail cadastrado.");
        }

        
        if (!userRepository.findByUsername(user.getUsername()).isEmpty()) {
            throw new RuntimeException("Nome de usuário já está em uso.");
        }

     
        return userRepository.save(user);
    }

    
    public User login(String username, String password) {
        List<User> users = userRepository.findByUsername(username);

        if (users.size() > 1) {
            throw new RuntimeException("Erro: há mais de um usuário com este nome de usuário cadastrado.");
        }

        if (users.size() == 1) {
            User user = users.get(0);
            if (user.getPassword().equals(password)) {
                return user;
            }
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
