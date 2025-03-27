package com.sportscenter.domain.service;

import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.UserRepository;

import java.sql.Timestamp;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.isActive() && user.getPassword().equals(password)) {

            user.setLast_login(new Timestamp(System.currentTimeMillis()));
            userRepository.update(user);
            return user;
        }
        return null;
    }

    public User register(User user, boolean isAdmin) {
        try {
            user.setRole(isAdmin ? "ADMIN" : "CONSUMER");
            user.setActive(true);

            if (user.getUsername() == null || user.getUsername().isEmpty() ||
                    user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Username y password son obligatorios");
            }

            if (userRepository.findByUsername(user.getUsername()) != null) {
                System.out.println("Error: El nombre de usuario ya existe");
                return null;
            }

            return userRepository.save(user);
        } catch (Exception e) {
            System.err.println("Error en UserService.register(): " + e.getMessage());
            return null;
        }
    }
}