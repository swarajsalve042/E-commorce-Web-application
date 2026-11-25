package com.portfolio.ecommerce;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiIntegrationTest {
    @Autowired MockMvc mvc;
    @Autowired ProductRepository repository;
    @Autowired ObjectMapper mapper;

    @BeforeEach
    void cleanDatabase() { repository.deleteAll(); }

    @Test
    void createsListsReadsAndDeletesProduct() throws Exception {
        String created = mvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"CI Keyboard\",\"price\":1499.00}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("CI Keyboard"))
            .andReturn().getResponse().getContentAsString();

        JsonNode product = mapper.readTree(created);
        long id = product.get("id").asLong();

        mvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("CI Keyboard"));

        mvc.perform(get("/api/products/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(1499.0));

        mvc.perform(delete("/api/products/{id}", id))
            .andExpect(status().isNoContent());

        mvc.perform(get("/api/products/{id}", id))
            .andExpect(status().isNotFound());
    }

    @Test
    void rejectsBlankNameAndNonPositivePrice() throws Exception {
        mvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"  \",\"price\":100}"))
            .andExpect(status().isBadRequest());

        mvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Invalid Price\",\"price\":0}"))
            .andExpect(status().isBadRequest());
    }
}
