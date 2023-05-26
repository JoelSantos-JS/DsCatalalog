package com.devsuperior.DsCatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DsCatalog.dto.ProductDTO;
import com.devsuperior.DsCatalog.exception.EnityNotFoundException;
import com.devsuperior.DsCatalog.model.Product;
import com.devsuperior.DsCatalog.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository pr;

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

        return new ProductDTO(category2);

    }

    public ProductDTO create(ProductDTO category) throws Exception {

        Product category1 = new Product();
        category1.setName(category.getName());

        Product savedCategory = pr.save(category1);

        ProductDTO savedProductDTO = new ProductDTO();
        savedProductDTO.setId(savedCategory.getId());
        savedProductDTO.setName(savedCategory.getName());

        return savedProductDTO;
    }

    public ProductDTO update(long id, ProductDTO category) {

        ProductDTO ProductDTO = findById(id);

        ProductDTO.setName(category.getName());

        Product category3 = new Product();
        category3.setId(ProductDTO.getId());
        category3.setName(ProductDTO.getName());

        Product category2 = pr.save(category3);

        return new ProductDTO(category2);

        // Atualiza o objeto (com id)

    }

    public void delete(long id) {
        try {
            pr.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DataIntegrityViolationException("Não é possível deletar uma categoria que possui produtos");
        }

    }

}
