package tqs5.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestController.class)
class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppService service;

    @Test
    void getAllCountriesTest() throws Exception {
        List<String> allCountries = Arrays.asList("Argentina", "Brazil", "Gibraltar", "Portugal");

        when(service.getCountries()).thenReturn(allCountries);

        mvc.perform(get("/api/country").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]", is("Argentina")))
                .andExpect(jsonPath("$[1]", is("Brazil")))
                .andExpect(jsonPath("$[2]", is("Gibraltar")))
                .andExpect(jsonPath("$[3]", is("Portugal")));
    }

    @Test
    void getCountryByNameTest() throws Exception {
        List<String> allCountries = Arrays.asList("Brazil", "Gibraltar");

        when(service.getCountries("Bra")).thenReturn(allCountries);

        mvc.perform(get("/api/country/Bra").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("Brazil")))
                .andExpect(jsonPath("$[1]", is("Gibraltar")));
    }

    @Test
    void getCountryStatisticsTest() throws Exception {
        String result = "{\"get\":\"statistics\",\"parameters\":{\"country\":\"Brazil\"},\"errors\":[],\"results\":1,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":215319917,\"cases\":{\"new\":\"+6254\",\"active\":257884,...}";

        when(service.getCountryStats("Brazil")).thenReturn(result);

        mvc.perform(get("/api/statistics/Brazil").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    void getCacheStatisticsTest() throws Exception {
        List<String> stats = Arrays.asList("Cache Requests: 5", "Cache Hits: 4", "Cache Misses: 1");

        when(service.getCacheStats()).thenReturn(stats);

        mvc.perform(get("/api/cache").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(stats)));
    }

}
