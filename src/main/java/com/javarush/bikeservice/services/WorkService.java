package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Work;
import com.javarush.bikeservice.repositories.WorkRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkService {
    private final WorkRepository workRepo;
    public WorkService(WorkRepository workRepo) {
        this.workRepo = workRepo;
    }
    public List<Work> getAllWorks() {
        return workRepo.findAll();
    }
    public List<Work> findAllWorksToDo(@NotNull List<Order> orders) {
        List<Work> worksToDo = new ArrayList<>();
        for (Order order : orders) {
            if (order.getInWork()) {
                worksToDo.addAll(order.getWorks());
            }
        }
        return worksToDo;
    }
    public List<Work> findNeededWorksForOrder(@NotNull Order order) {
        return new ArrayList<>(order.getWorks());
    }
    public List<Work> findByBeginning(String beginningOfName) {
        List<Work> findingWorks = new ArrayList<>();

        for (Work work : workRepo.findAll()) {
            String nameInLowerCase = work.getName().toLowerCase();
            String beginningInLowerCase = beginningOfName.toLowerCase();

            if (nameInLowerCase.startsWith(beginningInLowerCase)) {
                findingWorks.add(work);
            }
        }
        return findingWorks;
    }
    public void addOrUpdateWork(@NotNull Work work) {
        workRepo.save(work);
    }
    public void deleteByName(String name) {
         for (Work work : workRepo.findAll()) {
             if (work.getName().startsWith(name)) {
                 workRepo.delete(work);
                 break;
             }
         }
    }
}
