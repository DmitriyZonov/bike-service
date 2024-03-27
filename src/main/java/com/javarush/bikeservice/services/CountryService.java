package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.bike_service_entities.Country;
import com.javarush.bikeservice.repositories.jpa_repositories.CountryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.COUNTRY_NOT_FOUND_EXCEPTION;

@Service
public class CountryService {

    private final CountryRepository cRepo;

    public CountryService(CountryRepository cRepo) {
        this.cRepo = cRepo;
    }

    public List<Country> findAllCountries() {
        return cRepo.findAll();
    }
    public Country findById(Integer id) {
        Optional<Country> optional = cRepo.findById(id);

        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new NullPointerException(COUNTRY_NOT_FOUND_EXCEPTION);
        }
    }
    public Country findByName(String name) {
        Country country = null;
        for (Country c : cRepo.findAll()) {
            if(c.getName().startsWith(name)) {
                country = c;
                break;
            }
        }
        return country;
    }
    public void saveOrUpdateCountry(@NotNull Country country) {
        cRepo.save(country);
    }
    public void deleteById(Integer id) {
        Optional<Country> optional = cRepo.findById(id);

        if(optional.isPresent()) {
            cRepo.deleteById(id);
        } else {
            throw new NullPointerException(COUNTRY_NOT_FOUND_EXCEPTION);
        }
    }
}