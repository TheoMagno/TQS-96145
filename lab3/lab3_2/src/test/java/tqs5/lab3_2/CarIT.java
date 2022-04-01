package tqs5.lab3_2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import main.java.tqs5.lab3_2.Car;
import main.java.tqs5.lab3_2.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
     void whenValidInput_thenCreateCar() {
        Car camaro = new Car("Chevrolet", "Camaro");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/cars", camaro, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("Camaro");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200()  {
       createTestCar("Chevrolet", "Camaro");
       createTestCar("Audi", "A3");


       ResponseEntity<List<Car>> response = restTemplate
               .exchange("/api/employees", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
               });

       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Camaro", "A3");

   }


   private void createTestCar(String maker, String model) {
       Car car = new Car(maker, model);
       repository.saveAndFlush(car);
   }

}
