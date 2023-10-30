package com.example.restaurantmanagement.repositories;

import com.example.restaurantmanagement.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
