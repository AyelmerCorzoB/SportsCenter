package com.skeletonhexa.application.usecase.category;

import java.util.List;

import com.skeletonhexa.domain.entities.Category;
import com.skeletonhexa.domain.repository.CategoryRepository;

public class CategoryUseCase {
    private final CategoryRepository repository;

    public CategoryUseCase(CategoryRepository repository){
        this.repository = repository;
    }

    public void registerCategory(String name, String description){
        Category category = new Category(name,description);
        repository.save(category);
    }

    public Category getCategory(int id){
        return repository.searchByID(id);
    }

    public List<Category> ListCategorys(){
        return repository.listAll();
    }

    public void updateCategory(int id,String name, String description){
        Category category = new Category(id, name, description);
        repository.update(category);
    }

    public void deleteCategory(int id){
        repository.delete(id);
    }
}