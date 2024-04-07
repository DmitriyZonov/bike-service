package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import com.javarush.bikeservice.repositories.BikeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.BIKE_NOT_FOUND_EXCEPTION;

@Service
public class BikeService {
    private final BikeRepository bikeRepo;
    public BikeService(BikeRepository bikeRepo) {
        this.bikeRepo = bikeRepo;
    }
    public List<Bike> getAllBikes() {
        return bikeRepo.findAll();
    }
    public List<Bike> getAllBikesInWork() {
        List<Bike> bikeInWork = new ArrayList<>();
        for (Bike bike : bikeRepo.findAll()) {
            if(!bike.getOrders().isEmpty()) {
                for(Order order : bike.getOrders()) {
                    if(order.getInWork()) {
                        bikeInWork.add(bike);
                    }
                }
            }
        }
        return bikeInWork;
    }
    public Bike findByOrder (@NotNull Order order) {
        return order.getBike();
    }
    public Bike findById (Long id) {
        Optional<Bike> optional = bikeRepo.findById(id);
        Bike bike;
        if(optional.isPresent()) {
            bike = optional.get();
        } else {
            throw new NullPointerException(BIKE_NOT_FOUND_EXCEPTION);
        }
        return bike;
    }
    public void addOrUpdateBike(@NotNull Bike bike) {
        bikeRepo.save(bike);
    }
    public void deleteById(Long id) {
        Optional<Bike> optional = bikeRepo.findById(id);

        if(optional.isPresent()) {
            bikeRepo.deleteById(id);
        } else {
            throw new NullPointerException(BIKE_NOT_FOUND_EXCEPTION);
        }
    }
}
