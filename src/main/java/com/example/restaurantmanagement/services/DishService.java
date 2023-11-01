package com.example.restaurantmanagement.services;

import com.example.restaurantmanagement.entities.*;
import com.example.restaurantmanagement.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.restaurantmanagement.repositories.DishRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class DishService {
  private QDish qDish = QDish.dish;

  @Autowired
  private DishRepository dishRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public Dish create(DishRQ requestData) {
    Dish creationDishData = Dish.builder()
        .name(requestData.getName())
        .image(requestData.getImage())
        .description(requestData.getDescription())
        .price(requestData.getPrice())
        .build();

    Dish createdDish = dishRepository.save(creationDishData);
    return createdDish;
  }

  public List<Dish> findAll(Optional<Float> price) {
    List<Dish> dishes = new ArrayList<Dish>();
    if (price.isPresent()) {
      float filterPrice = price.get();
      Predicate predicate = qDish.price.gt(filterPrice);
      dishRepository.findAll(predicate).forEach(dishes::add);
    } else {
      dishRepository.findAll().forEach(dishes::add);
    }
    return dishes;
  }

  public Dish findById(Integer dishId) {
    return dishRepository
        .findById(dishId)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Dish not found"));
  }

  public List<Dish> findByIds(ArrayList<Integer> dishIds) {
    Predicate predicate = qDish.id.in(dishIds);
    List<Dish> dishes = new ArrayList<Dish>();
    dishRepository.findAll(predicate).forEach(dishes::add);
    return dishes;
  }
}
