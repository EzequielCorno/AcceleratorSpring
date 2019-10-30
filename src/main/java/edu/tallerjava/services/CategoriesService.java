package edu.tallerjava.services;

import edu.tallerjava.modelo.Category;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {
    List<Category> findAll();

    Optional<Category> findById(Long idCat);

    Category create(Category category);

    Optional<Category> findByCode(String code);

    Optional<Category> findByName(String name);

    Category findByCodeAndName(String code, String name);
}
