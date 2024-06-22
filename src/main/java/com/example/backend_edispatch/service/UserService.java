package com.example.backend_edispatch.service;

import com.example.backend_edispatch.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserById(Long id);
}
