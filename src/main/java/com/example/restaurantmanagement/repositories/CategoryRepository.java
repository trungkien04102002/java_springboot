package com.example.restaurantmanagement.repositories;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.example.restaurantmanagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// import org.springframework.data.repository.CrudRepository;

// public interface CategoryRepository extends CrudRepository<Category, Integer> {

// }
public interface CategoryRepository extends JpaRepository<Category, Integer>, QuerydslPredicateExecutor<Category> {
}
