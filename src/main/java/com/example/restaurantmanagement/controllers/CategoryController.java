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
import com.example.restaurantmanagement.entities.User;
import com.example.restaurantmanagement.models.CategoryRQ;
import com.example.restaurantmanagement.models.DishIdsRQ;
import com.example.restaurantmanagement.models.DishRQ;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "api/v1/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @PostMapping()
  @ResponseBody
  public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryRQ requestData) {
    Category newCategory = categoryService.create(requestData);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
  }

  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryService.findAll();
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
    Category category = categoryService.findById(id);
    return ResponseEntity.ok(category);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Category> deleteUserById(@PathVariable Integer id) {
    Category deletedCategory = categoryService.findById(id);
    categoryService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(deletedCategory);
  }
}
