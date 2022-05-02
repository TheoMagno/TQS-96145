package io.cucumber.skeleton;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin("file:///D:/Faculdade/TQS/TQS-96145/HW1/WebApp/")
public class RestController {
    private static Logger logger = LogManager.getLogger(RestController.class);

    @Autowired
    AppService service;

    @GetMapping("/api/country")
    public List<String> getAllCountries() throws IOException, InterruptedException {
        logger.info(service.getCountries());
        return service.getCountries();
    }

    @GetMapping("/api/country/{name}")
    public List<String> getCountryName(@PathVariable String name) throws IOException, InterruptedException {
        logger.info(service.getCountries(name));
        return service.getCountries(name);
    }

    @GetMapping("/api/statistics/{name}")
    public String getCountryStats(@PathVariable String name) throws IOException, InterruptedException {
        return service.getCountryStats(name);
    }

    @GetMapping("/api/cache")
    public List<String> getCache() {
        return service.getCacheStats();
    }
}
