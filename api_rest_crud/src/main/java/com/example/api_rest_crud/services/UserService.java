package com.example.api_rest_crud.services;

import com.example.api_rest_crud.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);
}
