package com.example.oldCar.dto;

import com.example.oldCar.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO {

    private Long id;
    private String modelName;
    private int mileage;
    private int price;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.modelName = car.getModelName();
        this.mileage = car.getMileage();
        this.price = car.getPrice();
    }
}