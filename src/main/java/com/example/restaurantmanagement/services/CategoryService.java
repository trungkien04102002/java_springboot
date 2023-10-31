package com.example.restaurantmanagement.services;

import com.example.restaurantmanagement.entities.*;
import com.example.restaurantmanagement.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.restaurantmanagement.repositories.CategoryRepository;
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
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private DishService dishService;
  @PersistenceContext
  private EntityManager entityManager;

  public Category create(CategoryRQ requestData) {
    Category creationCategoryData = Category.builder()
        .name(requestData.getName())
        .build();
    List<Dish> dishes = dishService.findByIds(requestData.getDishIds());
    creationCategoryData.setDishes(dishes);
    Category createdCategory = categoryRepository.save(creationCategoryData);
    return createdCategory;
  }

  public List<Category> findAll() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    return queryFactory
        .selectFrom(QCategory.category)
        .fetch();
  }

  public Category findById(Integer categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category not found"));
  }

  public List<Category> findByIds(ArrayList<Integer> categoryIds) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    return queryFactory
        .selectFrom(QCategory.category)
        .where(QCategory.category.id.in(categoryIds))
        .fetch();
  }

  public void deleteById(Integer categoryId) {
    categoryRepository.deleteById(categoryId);
  }
}
