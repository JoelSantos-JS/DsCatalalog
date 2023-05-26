package com.devsuperior.DsCatalog.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.DsCatalog.model.Category;
import com.devsuperior.DsCatalog.model.Product;
import com.devsuperior.DsCatalog.repository.CategoryRepostory;
import com.devsuperior.DsCatalog.repository.ProductRepository;

@Service
public class DbService {

    @Autowired
    CategoryRepostory cr;

    @Autowired
    ProductRepository pr;

    public List<Category> instaciaDados() {
        List<Category> list = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        list.add(new Category(1L, "Books"));
        list.add(new Category(2L, "Electronics"));
        list.add(new Category(3L, "Computers"));

        products.add(new Product(1L, "Macbook Pro",
                "Mack", 1200.0, "jjjk", Instant.now()));
        products.add(new Product(2L, "Notebbok Pro",
                "Mack", 1200.0, "jjjk", Instant.now()));
        products.add(new Product(3L, "Face Pro",
                "Mack", 1200.0, "jjjk", Instant.now()));
        products.add(new Product(4L, "Jogos Pro",
                "Mack", 1200.0, "jjjk", Instant.now()));

        pr.saveAll(products);
        cr.saveAll(list);

        return list;

    }

}
