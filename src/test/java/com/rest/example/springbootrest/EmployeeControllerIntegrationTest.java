package com.rest.example.springbootrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.example.springbootrest.model.Employee;
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
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createEmployee_valid_returns201() throws Exception {
        Employee emp = new Employee(0, "Test", "User", "test.user@example.com");
        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.firstName").value("Test"));
    }

    @Test
    void createEmployee_invalid_returns400() throws Exception {
        Employee emp = new Employee(0, "", "", "invalid-email");
        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEmployee_returns204() throws Exception {
        Employee emp = new Employee(0, "Del", "User", "del.user@example.com");
        String content = mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Employee saved = objectMapper.readValue(content, Employee.class);

        mockMvc.perform(delete("/api/v1/employees/" + saved.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/employees/" + saved.getId()))
                .andExpect(status().isNotFound());
    }
}
