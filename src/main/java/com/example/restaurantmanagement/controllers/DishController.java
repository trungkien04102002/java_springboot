package com.example.restaurantmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.restaurantmanagement.services.CategoryService;
import com.example.restaurantmanagement.services.DishService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.client.HttpClientErrorException;

import com.example.restaurantmanagement.entities.Category;
import com.example.restaurantmanagement.entities.Dish;
import com.example.restaurantmanagement.models.DishIdsRQ;
import com.example.restaurantmanagement.models.DishRQ;
import com.example.restaurantmanagement.repositories.CategoryRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "api/v1")
public class DishController {
  @Autowired
  private DishService dishService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private CategoryRepository categoryRepository;

  @PostMapping("/dishes")
  @ResponseBody
  public ResponseEntity<Dish> createDish(@RequestBody @Valid DishRQ requestData) {
    Dish newDish = dishService.create(requestData);
    return ResponseEntity.status(HttpStatus.CREATED).body(newDish);
  }

  @GetMapping("/dishes")
  @ResponseBody
  public ResponseEntity<List<Dish>> getAllDishes(@RequestParam(name = "price") Optional<Float> price) {
    List<Dish> dishes = dishService.findAll(price);
    return ResponseEntity.ok(dishes);
  }

  @GetMapping("/dishes/{id}")
  @ResponseBody
  public ResponseEntity<Dish> getDishById(@PathVariable Integer id) {
    Dish dish = dishService.findById(id);
    return ResponseEntity.ok(dish);
  }

  @PostMapping("/dishes/getDishIds")
  @ResponseBody
  public ResponseEntity<List<Dish>> getDishByIds(@RequestBody @Valid DishIdsRQ dishIds) {
    List<Dish> dishes = dishService.findByIds(dishIds.getDishIds());
    return ResponseEntity.ok(dishes);
  }

  // NEW API FOR MANY TO MANY RELATIONSHIP

  // Get all dishes of category
  @GetMapping("/categories/{categoryId}/dishes")
  @ResponseBody
  public ResponseEntity<List<Dish>> getDishesByCategoryId(@PathVariable(value = "categoryId") Integer categoryId) {
    Category category = categoryService.findById(categoryId);
    return ResponseEntity.ok(category.getDishes());
  }

  // Add dish for category
  @GetMapping("/categories/{categoryId}/dishes/{dishId}")
  @ResponseBody
  public ResponseEntity<Category> addDish(@PathVariable(value = "categoryId") Integer categoryId,
      @PathVariable(value = "dishId") Integer dishId) {
    Category category = categoryService.findById(categoryId);
    Dish dish = dishService.findById(dishId);
    category.addDish(dish);
    categoryRepository.save(category);
    return ResponseEntity.ok(category);
  }
}
