package com.devsuperior.DsCatalog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.DsCatalog.model.Category;
import com.devsuperior.DsCatalog.repository.CategoryRepostory;

@Service
public class CategoryService {

    @Autowired
    CategoryRepostory cr;

    public List<Category> findAll() {
        List<Category> category = cr.findAll();
        return category;
    }

    public Category findById(Long id) {
        Category category = cr.findById(id).get();

        return category;
    }

    public Category create(Category category) throws Exception {
        Category category1 = new Category();

        category1.setName(category.getName());

        return cr.save(category1); // Cria o objeto (com id
    }

}
