package com.devsuperior.DsCatalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.DsCatalog.model.Category;
import com.devsuperior.DsCatalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService cs;

    @GetMapping()

    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok().body(cs.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Category> findById(Long id) {
        return ResponseEntity.ok().body(cs.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws Exception {
        Category cat = cs.create(category);
        return ResponseEntity.ok().body(cat);

    }

}
