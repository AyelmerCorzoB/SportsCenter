package com.skeletonhexa.application.usecase.user;

import java.util.List;

import com.skeletonhexa.domain.entities.User;
import com.skeletonhexa.domain.model.RoleType;
import com.skeletonhexa.domain.repository.UserRepository;

public class UserUseCase {
    private final UserRepository repository;

    public UserUseCase(UserRepository repository){
        this.repository = repository;
    }

    public void registerUser(String username, String email, String password, RoleType role, boolean active){
        User user = new User(username, email, password, role, active);
        repository.save(user);
    }

    public User getUser(int id){
        return repository.searchById(id);
    }

    public List<User> ListUsers(){
        return repository.listAll();
    }

    public void updateCategry(int id, String username, String email, String password, RoleType role, boolean active){
        User user = new User(id, username, email, password, role, active);
        repository.update(user);
    }

    public void deleteUser(int id){
        repository.delete(id);
    }
}