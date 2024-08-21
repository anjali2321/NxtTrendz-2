package com.example.nxttrendz2;

import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.repository.CategoryJpaRepository;
import com.example.nxttrendz2.repository.ProductJpaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = { "/schema.sql", "/data.sql" })
public class NxtTrendzControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ProductJpaRepository productJpaRepository;

        @Autowired
        private CategoryJpaRepository categoryJpaRepository;

        @Autowired
        private JdbcTemplate jdbcTemplate;

        private HashMap<Integer, Object[]> categories = new HashMap<>();
        {
                categories.put(1, new Object[] { "Electronics", "Gadgets and devices for everyday use." });
                categories.put(2, new Object[] { "Books", "Novels, textbooks, and other reading materials." });
                categories.put(3, new Object[] { "Fashion", "Clothing, footwear, and accessories." });
                categories.put(4, new Object[] { "Home Appliances", "Essential tools for household tasks." }); // POST
                categories.put(5, new Object[] { "Toys", "Fun items for kids and adults alike." }); // PUT
        }

        private HashMap<Integer, Object[]> products = new HashMap<>();
        {
                products.put(1, new Object[] { "Laptop",
                                "A high-performance laptop suitable for gaming and professional tasks.", 50000.00, 1 });
                products.put(2, new Object[] { "Bluetooth Speaker", "A portable speaker with excellent sound quality.",
                                2500.00, 1 });
                products.put(3, new Object[] { "Mystery Novel", "A thrilling novel full of twists and turns.", 500.00,
                                2 });
                products.put(4, new Object[] { "History Textbook", "A comprehensive guide to world history.", 400.00,
                                2 });
                products.put(5, new Object[] { "Leather Jacket", "A stylish jacket made of genuine leather.", 2200.00,
                                3 });
                products.put(6, new Object[] { "Running Shoes", "Comfortable shoes perfect for jogging.", 700.00, 3 });
                products.put(7, new Object[] { "Blender", "An efficient kitchen tool for making smoothies.", 600.00,
                                4 }); // POST
                products.put(8, new Object[] { "Lego Set", "A 500-piece Lego set for building and creativity.", 300.00,
                                4 }); // PUT
        }

        @Test
        @Order(1)
        public void testGetCategories() throws Exception {
                mockMvc.perform(get("/categories")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(3)))

                                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$[0].description", Matchers.equalTo(categories.get(1)[1])))

                                .andExpect(jsonPath("$[1].id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$[1].description", Matchers.equalTo(categories.get(2)[1])))

                                .andExpect(jsonPath("$[2].id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$[2].description", Matchers.equalTo(categories.get(3)[1])));
        }

        @Test
        @Order(2)
        public void testGetProducts() throws Exception {
                mockMvc.perform(get("/categories/products")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(6)))

                                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].name", Matchers.equalTo(products.get(1)[0])))
                                .andExpect(jsonPath("$[0].description", Matchers.equalTo(products.get(1)[1])))
                                .andExpect(jsonPath("$[0].price", Matchers.equalTo(products.get(1)[2])))
                                .andExpect(jsonPath("$[0].category.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].category.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$[0].category.description",
                                                Matchers.equalTo(categories.get(1)[1])))

                                .andExpect(jsonPath("$[1].id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].name", Matchers.equalTo(products.get(2)[0])))
                                .andExpect(jsonPath("$[1].description", Matchers.equalTo(products.get(2)[1])))
                                .andExpect(jsonPath("$[1].price", Matchers.equalTo(products.get(2)[2])))
                                .andExpect(jsonPath("$[1].category.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[1].category.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$[1].category.description",
                                                Matchers.equalTo(categories.get(1)[1])))

                                .andExpect(jsonPath("$[2].id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].name", Matchers.equalTo(products.get(3)[0])))
                                .andExpect(jsonPath("$[2].description", Matchers.equalTo(products.get(3)[1])))
                                .andExpect(jsonPath("$[2].price", Matchers.equalTo(products.get(3)[2])))
                                .andExpect(jsonPath("$[2].category.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[2].category.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$[2].category.description",
                                                Matchers.equalTo(categories.get(2)[1])))

                                .andExpect(jsonPath("$[3].id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].name", Matchers.equalTo(products.get(4)[0])))
                                .andExpect(jsonPath("$[3].description", Matchers.equalTo(products.get(4)[1])))
                                .andExpect(jsonPath("$[3].price", Matchers.equalTo(products.get(4)[2])))
                                .andExpect(jsonPath("$[3].category.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[3].category.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$[3].category.description",
                                                Matchers.equalTo(categories.get(2)[1])))

                                .andExpect(jsonPath("$[4].id", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].name", Matchers.equalTo(products.get(5)[0])))
                                .andExpect(jsonPath("$[4].description", Matchers.equalTo(products.get(5)[1])))
                                .andExpect(jsonPath("$[4].price", Matchers.equalTo(products.get(5)[2])))
                                .andExpect(jsonPath("$[4].category.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[4].category.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$[4].category.description",
                                                Matchers.equalTo(categories.get(3)[1])))

                                .andExpect(jsonPath("$[5].id", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$[5].name", Matchers.equalTo(products.get(6)[0])))
                                .andExpect(jsonPath("$[5].description", Matchers.equalTo(products.get(6)[1])))
                                .andExpect(jsonPath("$[5].price", Matchers.equalTo(products.get(6)[2])))
                                .andExpect(jsonPath("$[5].category.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[5].category.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$[5].category.description",
                                                Matchers.equalTo(categories.get(3)[1])));
        }

        @Test
        @Order(3)
        public void testGetCategoryNotFound() throws Exception {
                mockMvc.perform(get("/categories/48")).andExpect(status().isNotFound());
        }

        @Test
        @Order(4)
        public void testGetCategoryById() throws Exception {
                mockMvc.perform(get("/categories/1")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(1)[1])));

                mockMvc.perform(get("/categories/2")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(2)[1])));

                mockMvc.perform(get("/categories/3")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(3)[1])));
        }

        @Test
        @Order(5)
        public void testGetProductNotFound() throws Exception {
                mockMvc.perform(get("/categories/products/48")).andExpect(status().isNotFound());
        }

        @Test
        @Order(6)
        public void testGetProductById() throws Exception {
                mockMvc.perform(get("/categories/products/1")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(1)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(1)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(1)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(1)[1])));

                mockMvc.perform(get("/categories/products/2")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(2)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(2)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(2)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(1)[1])));

                mockMvc.perform(get("/categories/products/3")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(3)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(3)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(3)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(2)[1])));

                mockMvc.perform(get("/categories/products/4")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(4)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(4)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(4)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(2)[1])));

                mockMvc.perform(get("/categories/products/5")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(5)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(5)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(5)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(3)[1])));

                mockMvc.perform(get("/categories/products/6")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(6)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(6)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(6)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(3)[1])));
        }

        @Test
        @Order(7)
        public void testPostCategory() throws Exception {
                String content = "{\"name\": \"" + categories.get(4)[0] + "\", \"description\": \""
                                + categories.get(4)[1] + "\"}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/categories")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(4)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(4)[1])));
        }

        @Test
        @Order(8)
        public void testAfterPostCategory() throws Exception {
                mockMvc.perform(get("/categories/4")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(4)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(4)[1])));
        }

        @Test
        @Order(9)
        public void testDbAfterPostCategory() throws Exception {
                Category category = categoryJpaRepository.findById(4).get();

                assertEquals(category.getId(), 4);
                assertEquals(category.getName(), categories.get(4)[0]);
                assertEquals(category.getDescription(), categories.get(4)[1]);
        }

        @Test
        @Order(10)
        public void testPostProduct() throws Exception {
                String content = "{\"name\": \"" + products.get(7)[0] + "\", \"description\": \""
                                + products.get(7)[1] + "\", \"price\": " + products.get(7)[2] + ", \"category\": {"
                                + "\"id\": " + products
                                                .get(7)[3]
                                + "}}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/categories/products")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(7)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(7)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(7)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(4)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(4)[1])));
        }

        @Test
        @Order(11)
        public void testAfterPostProduct() throws Exception {
                mockMvc.perform(get("/categories/products/7")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(7)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(7)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(7)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(4)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(4)[1])));
        }

        @Test
        @Order(12)
        public void testDbAfterPostProduct() throws Exception {
                Product product = productJpaRepository.findById(7).get();

                assertEquals(product.getId(), 7);
                assertEquals(product.getName(), products.get(7)[0]);
                assertEquals(product.getDescription(), products.get(7)[1]);
                assertEquals(product.getPrice(), products.get(7)[2]);
                assertEquals(product.getCategory().getId(), 4);
                assertEquals(product.getCategory().getName(), categories.get(4)[0]);
                assertEquals(product.getCategory().getDescription(), categories.get(4)[1]);
        }

        @Test
        @Order(13)
        public void testPutCategoryNotFound() throws Exception {
                String content = "{\"name\": \"" + categories.get(5)[0] + "\", \"description\": \""
                                + categories.get(5)[1] + "\"}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/categories/48")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isNotFound());
        }

        @Test
        @Order(14)
        public void testPutCategory() throws Exception {
                String content = "{\"name\": \"" + categories.get(5)[0] + "\", \"description\": \""
                                + categories.get(5)[1] + "\"}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/categories/4")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(5)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(5)[1])));
        }

        @Test
        @Order(15)
        public void testAfterPutCategory() throws Exception {

                mockMvc.perform(get("/categories/4")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(5)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(5)[1])));

        }

        @Test
        @Order(16)
        public void testDbAfterPutCategory() throws Exception {
                Category category = categoryJpaRepository.findById(4).get();

                assertEquals(category.getId(), 4);
                assertEquals(category.getName(), categories.get(5)[0]);
                assertEquals(category.getDescription(), categories.get(5)[1]);
        }

        @Test
        @Order(17)
        public void testPutProductNotFound() throws Exception {
                String content = "{\"name\": \"" + products.get(8)[0] + "\", \"description\": \""
                                + products.get(8)[1] + "\", \"price\": " + products.get(8)[2] + ", \"category\": {"
                                + "\"id\": " + products
                                                .get(8)[3]
                                + "}}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/categories/products/48")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isNotFound());
        }

        @Test
        @Order(18)
        public void testPutProduct() throws Exception {
                String content = "{\"name\": \"" + products.get(8)[0] + "\", \"description\": \""
                                + products.get(8)[1] + "\", \"price\": " + products.get(8)[2] + ", \"category\": {"
                                + "\"id\": " + products
                                                .get(8)[3]
                                + "}}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/categories/products/7")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(8)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(8)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(8)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(5)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(5)[1])));
        }

        @Test
        @Order(19)
        public void testAfterPutProduct() throws Exception {

                mockMvc.perform(get("/categories/products/7")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(products.get(8)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(products.get(8)[1])))
                                .andExpect(jsonPath("$.price", Matchers.equalTo(products.get(8)[2])))
                                .andExpect(jsonPath("$.category.id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.category.name", Matchers.equalTo(categories.get(5)[0])))
                                .andExpect(jsonPath("$.category.description", Matchers.equalTo(categories.get(5)[1])));

        }

        @Test
        @Order(20)
        public void testDbAfterPutProduct() throws Exception {
                Product product = productJpaRepository.findById(7).get();

                assertEquals(product.getId(), 7);
                assertEquals(product.getName(), products.get(8)[0]);
                assertEquals(product.getDescription(), products.get(8)[1]);
                assertEquals(product.getPrice(), products.get(8)[2]);
                assertEquals(product.getCategory().getId(), 4);
                assertEquals(product.getCategory().getName(), categories.get(5)[0]);
                assertEquals(product.getCategory().getDescription(), categories.get(5)[1]);
        }

        @Test
        @Order(21)
        public void testDeleteProductNotFound() throws Exception {
                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/categories/products/148");
                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(22)
        public void testDeleteProduct() throws Exception {
                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/categories/products/7");
                mockMvc.perform(mockRequest).andExpect(status().isNoContent());
        }

        @Test
        @Order(23)
        public void testAfterDeleteProduct() throws Exception {
                mockMvc.perform(get("/categories/products")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(6)))

                                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].name", Matchers.equalTo(products.get(1)[0])))
                                .andExpect(jsonPath("$[0].description", Matchers.equalTo(products.get(1)[1])))
                                .andExpect(jsonPath("$[0].price", Matchers.equalTo(products.get(1)[2])))
                                .andExpect(jsonPath("$[0].category.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].category.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$[0].category.description",
                                                Matchers.equalTo(categories.get(1)[1])))

                                .andExpect(jsonPath("$[1].id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].name", Matchers.equalTo(products.get(2)[0])))
                                .andExpect(jsonPath("$[1].description", Matchers.equalTo(products.get(2)[1])))
                                .andExpect(jsonPath("$[1].price", Matchers.equalTo(products.get(2)[2])))
                                .andExpect(jsonPath("$[1].category.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[1].category.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$[1].category.description",
                                                Matchers.equalTo(categories.get(1)[1])))

                                .andExpect(jsonPath("$[2].id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].name", Matchers.equalTo(products.get(3)[0])))
                                .andExpect(jsonPath("$[2].description", Matchers.equalTo(products.get(3)[1])))
                                .andExpect(jsonPath("$[2].price", Matchers.equalTo(products.get(3)[2])))
                                .andExpect(jsonPath("$[2].category.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[2].category.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$[2].category.description",
                                                Matchers.equalTo(categories.get(2)[1])))

                                .andExpect(jsonPath("$[3].id", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].name", Matchers.equalTo(products.get(4)[0])))
                                .andExpect(jsonPath("$[3].description", Matchers.equalTo(products.get(4)[1])))
                                .andExpect(jsonPath("$[3].price", Matchers.equalTo(products.get(4)[2])))
                                .andExpect(jsonPath("$[3].category.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[3].category.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$[3].category.description",
                                                Matchers.equalTo(categories.get(2)[1])))

                                .andExpect(jsonPath("$[4].id", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].name", Matchers.equalTo(products.get(5)[0])))
                                .andExpect(jsonPath("$[4].description", Matchers.equalTo(products.get(5)[1])))
                                .andExpect(jsonPath("$[4].price", Matchers.equalTo(products.get(5)[2])))
                                .andExpect(jsonPath("$[4].category.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[4].category.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$[4].category.description",
                                                Matchers.equalTo(categories.get(3)[1])))

                                .andExpect(jsonPath("$[5].id", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$[5].name", Matchers.equalTo(products.get(6)[0])))
                                .andExpect(jsonPath("$[5].description", Matchers.equalTo(products.get(6)[1])))
                                .andExpect(jsonPath("$[5].price", Matchers.equalTo(products.get(6)[2])))
                                .andExpect(jsonPath("$[5].category.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[5].category.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$[5].category.description",
                                                Matchers.equalTo(categories.get(3)[1])));
        }

        @Test
        @Order(24)
        public void testDeleteCategoryNotFound() throws Exception {
                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/categories/148");
                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(25)
        public void testDeleteCategory() throws Exception {
                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/categories/4");
                mockMvc.perform(mockRequest).andExpect(status().isNoContent());
        }

        @Test
        @Order(26)
        public void testAfterDeleteCategory() throws Exception {
                mockMvc.perform(get("/categories")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(3)))

                                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$[0].description", Matchers.equalTo(categories.get(1)[1])))

                                .andExpect(jsonPath("$[1].id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$[1].description", Matchers.equalTo(categories.get(2)[1])))

                                .andExpect(jsonPath("$[2].id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$[2].description", Matchers.equalTo(categories.get(3)[1])));
        }

        @Test
        @Order(27)
        public void testGetCategoryByProductId() throws Exception {
                mockMvc.perform(get("/products/1/category")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(1)[1])));

                mockMvc.perform(get("/products/2/category")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(1)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(1)[1])));

                mockMvc.perform(get("/products/3/category")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(2)[1])));

                mockMvc.perform(get("/products/4/category")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(2)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(2)[1])));

                mockMvc.perform(get("/products/5/category")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(3)[1])));

                mockMvc.perform(get("/products/6/category")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.name", Matchers.equalTo(categories.get(3)[0])))
                                .andExpect(jsonPath("$.description", Matchers.equalTo(categories.get(3)[1])));
        }

        @AfterAll
        public void cleanup() {
                jdbcTemplate.execute("drop table product");
                jdbcTemplate.execute("drop table category");
        }

}