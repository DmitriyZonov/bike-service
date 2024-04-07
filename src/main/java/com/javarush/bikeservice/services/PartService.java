package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Part;
import com.javarush.bikeservice.entities.bike_service_entities.Work;
import com.javarush.bikeservice.repositories.PartRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.PART_NOT_FOUND_EXCEPTION;

@Service
public class PartService {
    private final PartRepository partRepo;
    public PartService(PartRepository partRepo) {
        this.partRepo = partRepo;
    }
    public List<Part> getAllParts() {

        return partRepo.findAll();
    }
    public List<Part> getAllPartsInStock() {
        List<Part> partsInStock = new ArrayList<>();

        for (Part part : partRepo.findAll()) {
            if (part.getIsAvailableInStock()) {
                partsInStock.add(part);
            }
        }
        return partsInStock;
    }
    public List<Part> findAllNeededParts(@NotNull List<Order> orders) {
        List<Part> neededParts = new ArrayList<>();
        for (Order order : orders) {
            if (order.getInWork()) {
                for (Work work : order.getWorks()) {
                    if (!work.getParts().isEmpty()) {
                        neededParts.addAll(work.getParts());
                    }
                }
            }
        }
        return neededParts;
    }
    public List<Part> findNeededPartsForOrder(@NotNull Order order) {
        List<Part> neededParts = new ArrayList<>();
        for (Work work : order.getWorks()) {
            if (!work.getParts().isEmpty()) {
                neededParts.addAll(work.getParts());
            }
        }
        return neededParts;
    }
    public Part findById(Long id) {
        Optional<Part> optional = partRepo.findById(id);
        Part part;
        if(optional.isPresent()) {
            part = optional.get();
        } else {
            throw new NullPointerException(PART_NOT_FOUND_EXCEPTION);
        }
        return part;
    }
    public void addOrUpdatePart(@NotNull Part part) {
        partRepo.save(part);
    }
    public void deleteById(Long id) {
        Optional<Part> optional = partRepo.findById(id);

        if(optional.isPresent()) {
            partRepo.deleteById(id);
        } else {
            throw new NullPointerException(PART_NOT_FOUND_EXCEPTION);
        }
    }
}
