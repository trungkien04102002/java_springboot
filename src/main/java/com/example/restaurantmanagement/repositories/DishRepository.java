package com.example.restaurantmanagement.repositories;

import com.example.restaurantmanagement.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// public interface DishRepository extends CrudRepository<Dish, Integer> {

// }
public interface DishRepository extends JpaRepository<Dish, Integer>, QuerydslPredicateExecutor<Dish> {

}