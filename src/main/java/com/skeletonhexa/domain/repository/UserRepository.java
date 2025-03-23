package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.User;

public interface UserRepository {
    void save(User user);
    User searchById(int id);
    List<User> listAll();
    void update(User user);
    void delete(int id);
}
