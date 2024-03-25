package com.javarush.bikeservice.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface CrudRepository<T> {
    Integer save(T t);
    int update(T t);
    int deleteById(Integer id);
    List<T> findAll();
    T findById(Integer id);
}