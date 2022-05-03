package tqs5.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AppServiceTest {
    @Mock( lenient = true)
    private API api;

    @InjectMocks
    private AppService service;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        String result =  "{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Argentina\",\"Brazil\",\"Gibraltar\",\"Portugal\"]}";
        String bra = "{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Brazil\",\"Gibraltar\"]}";
        String stats = "{\"get\":\"statistics\",\"parameters\":{\"country\":\"Brazil\"},\"errors\":[],\"results\":1,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":215319917,\"cases\":{\"new\":\"+6254\",\"active\":257884,...}";

        Mockito.when(api.getAllCountries()).thenReturn(result);
        Mockito.when(api.getCountries("Bra")).thenReturn(bra);
        Mockito.when((api.getCountryStats("Brazil"))).thenReturn(stats);
    }

    @Test
    void when_search_all_countries() throws IOException, InterruptedException {
        List<String> countries = Arrays.asList("Argentina", "Brazil", "Gibraltar", "Portugal");
        List<String> found = service.getCountries();

        assertEquals(countries, found);
    }

    @Test
    void when_search_for_name() throws IOException, InterruptedException {
        List<String> countries = Arrays.asList("Brazil", "Gibraltar");
        List<String> found = service.getCountries("Bra");

        assertEquals(countries, found);
    }

    @Test
    void when_search_country_stats() throws IOException, InterruptedException {
        String continent = "South-America";
        String found = service.getCountryStats("Brazil");
        String continent_found = found.substring(found.indexOf("\"continent\":\"")+13, found.indexOf("\"continent\":\"")+26);

        assertEquals(continent, continent_found);
    }

}
