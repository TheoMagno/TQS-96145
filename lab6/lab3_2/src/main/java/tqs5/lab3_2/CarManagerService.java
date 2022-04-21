package tqs5.lab3_2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {
    @Autowired
    private CarRepository cars;

    public Car save(Car car) {
        return cars.save(car);
    }

    public List<Car> getAllCars() {
        return cars.findAll();
    }

    public Car getCarDetails(Long id) {
        return cars.findByCarId(id);
    }
}
