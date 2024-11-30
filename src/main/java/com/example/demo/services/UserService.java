package com.example.demo.services;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IServiceUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);  // Add a new user
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);  // Delete user by ID
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();  // Retrieve all users
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);  // Retrieve user by ID
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);  // Retrieve user by username
    }
}
