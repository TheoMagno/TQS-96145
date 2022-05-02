package io.cucumber.skeleton;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Hw1Application.class)
@AutoConfigureMockMvc
class ApiIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private API api;

    @Test
    void when_getAllCountries() throws Exception {
        mvc.perform(get("/api/country").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(233)))
                .andExpect(jsonPath("$[0]", is("Afghanistan")));
    }

    @Test
    void when_getBra() throws Exception {
        mvc.perform(get("/api/country/Bra").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("Brazil")));
    }

    @Test
    void when_getBrazilStats() throws Exception {
        mvc.perform(get("/api/country/Bra").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
                .andExpect(jsonPath("$", contains("Brazil")));
    }

}
