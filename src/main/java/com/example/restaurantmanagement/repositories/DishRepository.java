package com.example.restaurantmanagement.repositories;

import com.example.restaurantmanagement.entities.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Integer> {

}