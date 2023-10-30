package com.example.restaurantmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.restaurantmanagement.services.DishService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.client.HttpClientErrorException;

import com.example.restaurantmanagement.entities.Dish;
import com.example.restaurantmanagement.models.DishIdsRQ;
import com.example.restaurantmanagement.models.DishRQ;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "api/v1/dishes")
public class DishController {
  @Autowired
  private DishService dishService;

  @PostMapping()
  @ResponseBody
  public ResponseEntity<Dish> createDish(@RequestBody @Valid DishRQ requestData) {
    Dish newDish = dishService.create(requestData);
    return ResponseEntity.status(HttpStatus.CREATED).body(newDish);
  }

  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<Dish>> getAllDishes() {
    List<Dish> dishes = dishService.findAll();
    return ResponseEntity.ok(dishes);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Dish> getDishById(@PathVariable Integer id) {
    Dish dish = dishService.findById(id);
    return ResponseEntity.ok(dish);
  }

  @PostMapping("/getDishIds")
  @ResponseBody
  public ResponseEntity<List<Dish>> getDishByIds(@RequestBody @Valid DishIdsRQ dishIds) {
    System.out.println("dishIds: " + dishIds);
    List<Dish> dishes = dishService.findByIds(dishIds.getDishIds());
    return ResponseEntity.ok(dishes);
  }
}
