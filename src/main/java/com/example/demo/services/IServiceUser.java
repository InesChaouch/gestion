package com.example.demo.services;

import com.example.demo.entities.User;

import java.util.List;

public interface IServiceUser {
    void addUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
}
