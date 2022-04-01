package main.java.tqs5.lab3_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    public Car findByCarId(Long id);
    public List<Car> findAll();
}
