package com.sportscenter.domain.service;

import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private User currentUser; // Variable para mantener el usuario actual

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.isActive() && user.getPassword().equals(password)) {
            user.setLast_login(new Timestamp(System.currentTimeMillis()));
            userRepository.update(user);
            this.currentUser = user; // Establecer el usuario actual
            return user;
        }
        this.currentUser = null; // Limpiar el usuario actual si la autenticación falla
        return null;
    }

    public User register(User user, boolean isAdmin) {
        try {

            if (user.getUsername() == null || user.getUsername().isEmpty() ||
                    user.getPassword() == null || user.getPassword().isEmpty()) {
                System.err.println("Error: Username y password son obligatorios");
                return null;
            }

            if (userRepository.findByUsername(user.getUsername()) != null) {
                System.err.println("Error: El nombre de usuario '" + user.getUsername() + "' ya está registrado");
                return null;
            }

            user.setRole(isAdmin ? "ADMIN" : "CONSUMER");
            user.setActive(true);

            return userRepository.save(user);

        } catch (Exception e) {
            System.err.println("Error técnico al registrar usuario: " + e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de usuarios: " + e.getMessage(), e);
        }
    }

    public List<User> getUsersByRole(String role) {
        try {
            return userRepository.findAll().stream()
                    .filter(user -> user.getRole().equalsIgnoreCase(role))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener usuarios por rol: " + e.getMessage(), e);
        }
    }

    public User getUserById(int id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar usuario por ID: " + e.getMessage(), e);
        }
    }

    public void updateUser(User user) {
        try {
            if (userRepository.findById(user.getId()) == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }
            userRepository.update(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }

    public void toggleUserStatus(int userId, boolean active) {
        try {
            User user = userRepository.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            user.setActive(active);
            userRepository.update(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar estado de usuario: " + e.getMessage(), e);
        }
    }
}