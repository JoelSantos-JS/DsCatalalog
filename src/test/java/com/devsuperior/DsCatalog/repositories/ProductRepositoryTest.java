package com.devsuperior.DsCatalog.repositories;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devsuperior.DsCatalog.model.Product;
import com.devsuperior.DsCatalog.repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    public void deleteShouldDeleteObjectWhenIdExists() {
        long existingId = 1L;
        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product product = new Product("Name", "Description", 10.0, "https://link.com", Instant.now());
        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(1L, product.getId());

    }

}
