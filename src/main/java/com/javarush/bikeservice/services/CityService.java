package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.City;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import com.javarush.bikeservice.repositories.jpa_repositories.CityRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.CITY_NOT_FOUND_EXCEPTION;

@Service
public class CityService {
    private final CityRepository cityRepo;
    public CityService(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    public List<City> findAllCities() {
        return cityRepo.findAll();
    }
    public City findById(Integer id) {
        Optional<City> optional = cityRepo.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NullPointerException(CITY_NOT_FOUND_EXCEPTION);
        }

    }
    public List<Client> getAllClientsFromCity(@NotNull City city) {
        return new ArrayList<>(city.getClients());
    }
    public List<Order> getAllOrdersFromCity(@NotNull City city) {
        List<Order> orders = new ArrayList<>();
        for(Client client : city.getClients()) {
            orders.addAll(client.getOrders());
        }
        return orders;
    }
    public void addOrUpdateCity(@NotNull City city) {
        cityRepo.save(city);
    }
    public void deleteCityById(Integer id) {
        Optional<City> optional = cityRepo.findById(id);

        if(optional.isPresent()) {
            cityRepo.deleteById(id);
        } else {
            throw new NullPointerException(CITY_NOT_FOUND_EXCEPTION);
        }
    }
}
