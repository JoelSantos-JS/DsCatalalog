package com.devsuperior.DsCatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DsCatalog.dto.CategoryDTO;
import com.devsuperior.DsCatalog.dto.ProductDTO;
import com.devsuperior.DsCatalog.exception.EnityNotFoundException;
import com.devsuperior.DsCatalog.exception.ResourceExceptionHandler;
import com.devsuperior.DsCatalog.model.Category;
import com.devsuperior.DsCatalog.model.Product;
import com.devsuperior.DsCatalog.repository.CategoryRepostory;
import com.devsuperior.DsCatalog.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository pr;

    @Autowired
    private CategoryRepostory cr;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> products = pr.findAll();

        List<ProductDTO> productDTOs = products.stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
        return productDTOs;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> product = pr.findAll(pageRequest);

        return product.map(e -> new ProductDTO(e));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> category = pr.findById(id);
        Product category2 = category.orElseThrow(() -> new EnityNotFoundException("Entity not found"));

        return new ProductDTO(category2, category2.getCategories());

    }

    @Transactional
    public ProductDTO create(ProductDTO category) throws Exception {

        Product category1 = new Product();

        copyDtoToEntity(category, category1);

        category1 = pr.save(category1);

        return new ProductDTO(category1);
    }

    private void copyDtoToEntity(ProductDTO category, Product category1) {
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        category1.setImgUrl(category.getImgUrl());
        category1.setPrice(category.getPrice());
        category1.getCategories().clear();
        for (CategoryDTO categoryDTO : category.getCategories()) {
            Category category5 = cr.getOne(categoryDTO.getId());
            category1.getCategories().add(category5);
        }

    }

    @Transactional
    public ProductDTO update(long id, ProductDTO category) {

        try {
            Product product = pr.getOne(id);
            copyDtoToEntity(category, product);
            product = pr.save(product);

            ProductDTO productDTO = new ProductDTO(product);

            return productDTO;

        } catch (Exception e) {
            // TODO: handle exception
            throw new EnityNotFoundException("Entity not found");
        }

        // Atualiza o objeto (com id)

    }

    public void delete(long id) throws ResourceNotFoundException {
        try {
            pr.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);

        } catch (Exception e) {
            // TODO: handle exception
            throw new DataIntegrityViolationException("Não é possível deletar uma categoria que possui produtos");
        }

    }

}
