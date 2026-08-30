package com.example.spring_6_webapp.services;

import com.example.spring_6_webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
