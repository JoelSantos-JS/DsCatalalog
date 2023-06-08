package com.devsuperior.DsCatalog.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.DsCatalog.model.Category;
import com.devsuperior.DsCatalog.model.Product;
import com.devsuperior.DsCatalog.model.Role;
import com.devsuperior.DsCatalog.model.User;
import com.devsuperior.DsCatalog.repository.CategoryRepostory;
import com.devsuperior.DsCatalog.repository.ProductRepository;
import com.devsuperior.DsCatalog.repository.RoleRepository;
import com.devsuperior.DsCatalog.repository.UserRepository;

@Service
public class DbService {

    @Autowired
    CategoryRepostory cr;

    @Autowired
    ProductRepository pr;

    @Autowired
    RoleRepository rr;

    @Autowired
    UserRepository ur;

    public List<Category> instaciaDados() {
        List<Category> list = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        List<User> users = new ArrayList<>();

        list.add(new Category(1L, "Books"));
        list.add(new Category(2L, "Electronics"));
        list.add(new Category(3L, "Computers"));

        roles.add(new Role(1L, "ROLE_ADMIN"));
        roles.add(new Role(2L, "ROLE_USER"));
        roles.add(new Role(3L, "ROLE_MANAGER"));
        roles.add(new Role(4L, "ROLE_SUPERVISOR"));

        users.add(new User(1L, "Joel", "Jose", "joeltere@gmail.com", "joel"));
        users.add(new User(2L, "Maria", "Jose", "maria@gmail.com", "maria"));

        products.add(new Product(1L, "Macbook Pro", "Mack", 1200.0, "jjjk", Instant.now()));
        products.add(new Product(2L, "Notebbok Pro", "Mack", 1200.0, "jjjk", Instant.now()));
        products.add(new Product(3L, "Face Pro", "Mack", 1200.0, "jjjk", Instant.now()));
        products.add(new Product(4L, "Jogos Pro", "Mack", 1200.0, "jjjk", Instant.now()));

        rr.saveAll(roles);
        pr.saveAll(products);
        cr.saveAll(list);
        ur.saveAll(users);

        return list;

    }

}