package com.sportscenter.application.usecase.category;

import com.sportscenter.domain.entities.Category;
import com.sportscenter.domain.repository.CategoryRepository;
import java.util.List;

public class CategoryUseCase {
    private final CategoryRepository repository;

    public CategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public void registerCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        repository.save(category);
    }

    public Category getCategoryById(int id) {
        return repository.findById(id);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public void updateCategory(int id, String name, String description) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        repository.update(category);
    }

    public void deleteCategory(int id) {
        repository.delete(id);
    }
}