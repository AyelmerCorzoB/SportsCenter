package com.sportscenter.application.usecase.color;

import com.sportscenter.domain.entities.Color;
import com.sportscenter.domain.repository.ColorRepository;
import java.util.List;

public class ColorUseCase {
    private final ColorRepository repository;

    public ColorUseCase(ColorRepository repository) {
        this.repository = repository;
    }

    public void registerColor(String name, String hexCode) {
        Color color = new Color();
        color.setName(name);
        color.setHexCode(hexCode);
        repository.save(color);
    }

    public Color getColorById(int id) {
        return repository.findById(id);
    }

    public List<Color> getAllColors() {
        return repository.findAll();
    }

    public void updateColor(int id, String name, String hexCode) {
        Color color = new Color();
        color.setId(id);
        color.setName(name);
        color.setHexCode(hexCode);
        repository.update(color);
    }

    public void deleteColor(int id) {
        repository.delete(id);
    }
}