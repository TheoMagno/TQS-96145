package main.java.tqs5.lab3_2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
    @Autowired
    private CarManagerService cars;

    @PostMapping("/cars" )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = cars.save(car);
        return new ResponseEntity<>(saved, status);
    }
    
    @GetMapping("/cars" )
    public List<Car> getAllCars() {
        return cars.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable(value = "id") Long id) {
        return cars.getCarDetails(id);
    }
}
