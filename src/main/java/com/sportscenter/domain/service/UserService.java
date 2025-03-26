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

    public boolean register(User user, boolean isAdminRegistration) {
        try {
            if (!isAdminRegistration) {
                user.setRole("CONSUMER");
            }

            if (userRepository.findByUsername(user.getUsername()) != null) {
                System.out.println("El nombre de usuario ya existe");
                return false;
            }

            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}