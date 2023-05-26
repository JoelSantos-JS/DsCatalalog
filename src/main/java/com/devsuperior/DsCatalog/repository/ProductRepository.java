package com.devsuperior.DsCatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.DsCatalog.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
