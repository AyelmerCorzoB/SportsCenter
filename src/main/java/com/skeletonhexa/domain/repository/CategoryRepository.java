package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Category;

public interface CategoryRepository {
    void save(Category category);
    Category searchByID(int id);
    List<Category> listAll();
    void update(Category category);
    void delete(int id);
}
