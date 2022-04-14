package tqs5.lab3_2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs5.lab3_2.Car;
import tqs5.lab3_2.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    @Test
    void whenFindCamaroByCarId_thenReturnCamaroCar() {
        // arrange a new employee and insert into db
        Car camaro = new Car("Chevrolet", "Camaro");
        entityManager.persistAndFlush(camaro); //ensure data is persisted at this point

        // test the query method of interest
        Car found = repository.findByCarId(camaro.getCarId());
        assertThat( found ).isEqualTo(camaro);
    }

    @Test
    void whenInvalidCarId_thenReturnNull() {
        Car fromDb = repository.findByCarId(50L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        Car camaro = new Car("Chevrolet", "Camaro");
        Car a3 = new Car("Audi", "A3");
        Car tesla = new Car("Tesla", "Model S");

        entityManager.persist(camaro);
        entityManager.persist(a3);
        entityManager.persist(tesla);
        entityManager.flush();

        List<Car> allCars = repository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(camaro.getModel(), a3.getModel(), tesla.getModel());
    }
}
