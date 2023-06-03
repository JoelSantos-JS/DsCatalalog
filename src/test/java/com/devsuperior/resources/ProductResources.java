package com.devsuperior.resources;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import com.devsuperior.DsCatalog.dto.ProductDTO;
import com.devsuperior.DsCatalog.model.Product;
import com.devsuperior.DsCatalog.services.ProductService;

@WebMvcTest(ProductResources.class)
public class ProductResources {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductDTO product;

    private PageImpl<ProductDTO> page;

    @BeforeEach
    void setUp() throws Exception {
        product = new ProductDTO(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.now());

        page = new PageImpl<>(List.of(product));

        when(productService.findAllPaged(null)).thenReturn(page);
    }

}
