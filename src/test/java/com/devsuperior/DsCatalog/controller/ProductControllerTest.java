package com.devsuperior.DsCatalog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.devsuperior.DsCatalog.dto.ProductDTO;
import com.devsuperior.DsCatalog.services.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

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

        when(productService.findAllPaged(any())).thenReturn(page);
    }

}
