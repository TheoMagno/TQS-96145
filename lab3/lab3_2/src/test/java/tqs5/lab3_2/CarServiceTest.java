package tqs5.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import main.java.tqs5.lab3_2.Car;
import main.java.tqs5.lab3_2.CarManagerService;
import main.java.tqs5.lab3_2.CarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock( lenient = true)
    private CarRepository repository;

    @InjectMocks
    private CarManagerService service;
    
    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Car camaro = new Car("Chevrolet", "Camaro");
        camaro.setCarId(111L);

        Car a3 = new Car("Audi", "A3");

        List<Car> allCars = Arrays.asList(camaro, a3);

        Mockito.when(repository.findByCarId(camaro.getCarId())).thenReturn(camaro);
        Mockito.when(repository.findByCarId(a3.getCarId())).thenReturn(a3);
        Mockito.when(repository.findByCarId(50L)).thenReturn(null);
        Mockito.when(repository.findAll()).thenReturn(allCars);
        Mockito.when(repository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
     void whenSearchValidId_thenCarShouldBeFound() {
        Long id = 111L;
        Car found = service.getCarDetails(id);

        assertThat(found.getModel()).isEqualTo("Camaro");
    }

    @Test
    void whenSearchInvalidId_thenCarShouldNotBeFound() {
       Car fromDb = service.getCarDetails(50L);
       assertThat(fromDb).isNull();

       verifyFindByCarIdIsCalledOnce(50L);
    }

    @Test
    void given2Cars_whengetAll_thenReturn2Records() {
        Car camaro = new Car("Chevrolet", "Camaro");
        Car a3 = new Car("Audi", "A3");

       List<Car> allCars = service.getAllCars();
       verifyFindAllCarsIsCalledOnce();
       assertThat(allCars).hasSize(2).extracting(Car::getModel).contains(camaro.getModel(), a3.getModel());
   }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
    }

    private void verifyFindByCarIdIsCalledOnce(Long id) {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByCarId(id);
    }
}
