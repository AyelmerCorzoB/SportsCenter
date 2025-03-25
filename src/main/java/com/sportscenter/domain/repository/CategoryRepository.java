package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.Category;

public interface CategoryRepository {
    void save(Category category);
    Category findById(int id);
    List<Category> findAll();
    void update(Category category);
    void delete(int id);
}