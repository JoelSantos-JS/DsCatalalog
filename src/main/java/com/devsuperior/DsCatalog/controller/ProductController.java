package com.devsuperior.DsCatalog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.DsCatalog.dto.ProductDTO;
import com.devsuperior.DsCatalog.services.ProductService;
import com.devsuperior.DsCatalog.services.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService pr;

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Page<ProductDTO> list = pr.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(pr.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createCategory(@Valid @RequestBody ProductDTO category) throws Exception {
        ProductDTO cat = pr.create(category);
        return ResponseEntity.status(201).body(cat);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateCategory(@Valid @PathVariable Long id, @RequestBody ProductDTO category) {
        ProductDTO cat = pr.update(id, category);

        return ResponseEntity.ok().body(cat);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws ResourceNotFoundException {
        pr.delete(id);
        return ResponseEntity.noContent().build();
    }

}
