package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.bike_service_entities.Brand;
import com.javarush.bikeservice.repositories.BrandRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.javarush.bikeservice.constants.ExceptionConstants.BRAND_NOT_FOUND_EXCEPTION;
import static java.util.Objects.isNull;

@Service
public class BrandService {
    private final BrandRepository bRepo;
    public BrandService(BrandRepository bRepo) {
        this.bRepo = bRepo;
    }
    public List<Brand> findAllBrands() {
        return bRepo.findAll();
    }
    public Brand findBrandById(Integer id) {
        Brand brand = bRepo.findById(id);

        if(!isNull(brand)) {
            return brand;
        } else {
            throw new NullPointerException(BRAND_NOT_FOUND_EXCEPTION);
        }
    }
    public void saveNewBrand(@NotNull Brand brand) {
        bRepo.save(brand);
    }
    public void updateNBrand(@NotNull Brand brand) {
        bRepo.update(brand);
    }
    public void deleteById(Integer id) {
        Brand brand = bRepo.findById(id);

        if(!isNull(brand)) {
            bRepo.deleteById(id);
        } else {
            throw new NullPointerException(BRAND_NOT_FOUND_EXCEPTION);
        }
    }
}
