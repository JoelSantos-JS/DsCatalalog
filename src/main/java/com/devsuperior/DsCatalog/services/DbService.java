package com.devsuperior.DsCatalog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.DsCatalog.model.Category;
import com.devsuperior.DsCatalog.repository.CategoryRepostory;

@Service
public class DbService {

    @Autowired
    CategoryRepostory cr;

    public List<Category> instaciaDados() {
        List<Category> list = new ArrayList<>();

        list.add(new Category(1L, "Books"));
        list.add(new Category(2L, "Electronics"));
        list.add(new Category(3L, "Computers"));

        cr.saveAll(list);

        return list;

    }

}
