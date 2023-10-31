package com.example.restaurantmanagement.services;

import com.example.restaurantmanagement.entities.*;
import com.example.restaurantmanagement.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.restaurantmanagement.repositories.DishRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.ArrayList;

@Service
public class DishService {
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

  public List<Dish> findAll() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    return queryFactory
        .selectFrom(QDish.dish)
        .fetch();
  }

  public Dish findById(Integer dishId) {
    return dishRepository
        .findById(dishId)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Dish not found"));
  }

  public List<Dish> findByIds(ArrayList<Integer> dishIds) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    return queryFactory
        .selectFrom(QDish.dish)
        .where(QDish.dish.id.in(dishIds))
        .fetch();
  }
}
