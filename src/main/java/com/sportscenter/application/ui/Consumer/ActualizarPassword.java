package com.sportscenter.application.ui.Consumer;

import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.UserRepository;

public class ActualizarPassword {

    private final UserRepository userRepository;

    public ActualizarPassword(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void cambiar(int id, String nuevaContraseña) {
        if (nuevaContraseña == null || nuevaContraseña.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

        // Buscar al usuario actual
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }

        // Actualizar la contraseña
        user.setPassword(nuevaContraseña); // En un caso real, deberías hashearla
        userRepository.update(user);
    }
}
