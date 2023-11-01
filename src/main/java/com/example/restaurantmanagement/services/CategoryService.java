package com.example.restaurantmanagement.services;

import com.example.restaurantmanagement.entities.*;
import com.example.restaurantmanagement.models.*;
import com.example.restaurantmanagement.repositories.CategoryRepository;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.ArrayList;

@Service
public class CategoryService {
  private QCategory qCategory = QCategory.category;
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
    List<Category> categories = new ArrayList<Category>();
    categoryRepository.findAll().forEach(categories::add);
    return categories;
  }

  public Category findById(Integer categoryId) {
    Predicate predicate = qCategory.id.eq(categoryId);
    Category category = categoryRepository.findOne(predicate).orElse(null);
    if (category == null) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category not found");
    }
    return category;

  }

  public List<Category> findByIds(ArrayList<Integer> categoryIds) {
    Predicate predicate = qCategory.id.in(categoryIds);
    List<Category> categories = new ArrayList<Category>();
    categoryRepository.findAll(predicate).forEach(categories::add);
    return categories;
  }

  public void deleteById(Integer categoryId) {
    categoryRepository.deleteById(categoryId);
  }
}
