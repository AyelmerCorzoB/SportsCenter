package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.User;

public interface UserRepository {
    void save(User user);
    User findById(int id);
    List<User> findAll();
    User findByUsername(String username);
    void update(User user);
    void delete(int id);
}