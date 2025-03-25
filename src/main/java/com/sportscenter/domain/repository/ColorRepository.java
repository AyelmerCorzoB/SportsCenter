package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.Color;

public interface ColorRepository {
    void save(Color color);
    Color findById(int id);
    List<Color> findAll();
    void update(Color color);
    void delete(int id);
}
