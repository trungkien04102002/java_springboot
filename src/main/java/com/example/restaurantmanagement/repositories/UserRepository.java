package com.example.restaurantmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.example.restaurantmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// public interface UserRepository extends CrudRepository<User, Long> {
// }

public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User> {
}
