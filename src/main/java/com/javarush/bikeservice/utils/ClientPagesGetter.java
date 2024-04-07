package com.javarush.bikeservice.utils;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

public class ClientPagesGetter {

    @NotNull
    public static Page<Client> getClientPages(int pageSize, int currentPage, int startItem, List<Client> clientsFromDB) {
        List<Client> clientsInProgress;

        if (clientsFromDB.size() < startItem) {
            clientsInProgress = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clientsFromDB.size());
            clientsInProgress = clientsFromDB.subList(startItem, toIndex);
        }
        Page<Client> clientPage = new PageImpl<Client>(clientsInProgress, PageRequest.of(currentPage, pageSize), clientsFromDB.size());
        return clientPage;
    }
}
