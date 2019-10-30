package edu.tallerjava.services;


import edu.tallerjava.modelo.Category;
import edu.tallerjava.repositorios.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service("CategoriesService")
public class CategoriesServiceImpl implements CategoriesService {



    @Autowired
    private CategoryRepository catRepository;

    @Override
    public List<Category> findAll() {
        return catRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long idCat) {
        return catRepository.findById(idCat);
    }

    @Override
    @Transactional(readOnly = false)
    public Category create(Category category) {
        Optional<Category> catFound = catRepository.findByCodigo(category.getCodigo());
        if (catFound.isPresent()) {
            return null;
        } else {
            Category catToSave = new Category(category.getCodigo(), category.getNombre(), category.getPermalink());
            catRepository.save(catToSave);
            return catToSave;
        }
    }

    @Override
    public Optional<Category> findByCode(String code) {
        List<Category> listCategories = catRepository.findAll();
        return Optional.of(listCategories.stream().filter(category -> category.getCodigo().equals(code)).findFirst().get());
    }

    @Override
    public Optional<Category> findByName(String name) {
        List<Category> listCategories = catRepository.findAll();
        return Optional.of(listCategories.stream().filter(category -> category.getNombre().equals(name)).findFirst().get());
    }

    @Override
    public Category findByCodeAndName(String code, String name) {
        List<Category> listCategories = catRepository.findAll();
        return listCategories.stream().filter(category -> category.getCodigo().equals(code) && category.getNombre().equals(name)).findFirst().get();
    }
}
