package com.example.restaurantmanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.restaurantmanagement.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
