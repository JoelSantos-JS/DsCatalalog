package com.devsuperior.DsCatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devsuperior.DsCatalog.model.Product;
import com.devsuperior.DsCatalog.repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        long existingId = 1L;
        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }
}
