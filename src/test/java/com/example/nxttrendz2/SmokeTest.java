package com.example.nxttrendz2;

import com.example.nxttrendz2.controller.CategoryController;
import com.example.nxttrendz2.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private CategoryController categoryController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(productController).isNotNull();
        assertThat(categoryController).isNotNull();
    }
}
