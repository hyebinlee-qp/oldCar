package com.example.oldCar.controller;

import com.example.oldCar.dto.CarDTO;
import com.example.oldCar.entity.Car;
import com.example.oldCar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
public class CarRestController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 차량 매물이 없습니다. id=" + id));
        return ResponseEntity.ok(new CarDTO(car));
    }

    @PostMapping
    public CarDTO createCar(@RequestBody CarDTO dto) {
        Car car = new Car();
        car.setModelName(dto.getModelName());
        car.setMileage(dto.getMileage());
        car.setPrice(dto.getPrice());
        
        Car saved = carRepository.save(car);
        return new CarDTO(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO dto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 차량 매물이 없습니다. id=" + id));
        
        car.setModelName(dto.getModelName());
        car.setMileage(dto.getMileage());
        car.setPrice(dto.getPrice());
        
        Car updated = carRepository.save(car);
        return ResponseEntity.ok(new CarDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 차량 매물이 없습니다. id=" + id));
        
        carRepository.delete(car);
        return ResponseEntity.ok("차량 매물이 성공적으로 삭제되었습니다. id=" + id);
    }
}