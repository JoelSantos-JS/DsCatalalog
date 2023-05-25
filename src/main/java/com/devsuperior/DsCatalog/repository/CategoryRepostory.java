package com.devsuperior.DsCatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.DsCatalog.model.Category;

@Repository
public interface CategoryRepostory extends JpaRepository<Category, Long> {

}
