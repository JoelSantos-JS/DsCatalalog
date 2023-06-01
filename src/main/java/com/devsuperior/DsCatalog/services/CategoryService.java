package com.devsuperior.DsCatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DsCatalog.dto.CategoryDTO;
import com.devsuperior.DsCatalog.exception.EnityNotFoundException;
import com.devsuperior.DsCatalog.model.Category;
import com.devsuperior.DsCatalog.repository.CategoryRepostory;

@Service
public class CategoryService {

    @Autowired
    CategoryRepostory cr;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> category = cr.findAll();

        List<CategoryDTO> categoryDTOs = category.stream().map(e -> new CategoryDTO(e)).collect(Collectors.toList());
        return categoryDTOs;
    }

    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> category = cr.findAll(pageable);

        return category.map(e -> new CategoryDTO(e));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> category = cr.findById(id);
        Category category2 = category.orElseThrow(() -> new EnityNotFoundException("Entity not found"));

        return new CategoryDTO(category2);

    }

    public CategoryDTO create(CategoryDTO category) throws Exception {

        Category category1 = new Category();
        category1.setName(category.getName());

        Category savedCategory = cr.save(category1);

        CategoryDTO savedCategoryDTO = new CategoryDTO();
        savedCategoryDTO.setId(savedCategory.getId());
        savedCategoryDTO.setName(savedCategory.getName());

        return savedCategoryDTO;
    }

    public CategoryDTO update(long id, CategoryDTO category) {

        CategoryDTO categoryDTO = findById(id);

        categoryDTO.setName(category.getName());

        Category category3 = new Category();
        category3.setId(categoryDTO.getId());
        category3.setName(categoryDTO.getName());

        Category category2 = cr.save(category3);

        return new CategoryDTO(category2);

        // Atualiza o objeto (com id)

    }

    public void delete(long id) {
        try {
            cr.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DataIntegrityViolationException("Não é possível deletar uma categoria que possui produtos");
        }

    }

}
