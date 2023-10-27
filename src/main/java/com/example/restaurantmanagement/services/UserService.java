package com.example.restaurantmanagement.services;

import java.util.Optional;
import com.example.restaurantmanagement.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.restaurantmanagement.repositories.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

// import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public List<User> findAll() {
    // Using pure JPA
    List<User> users = new ArrayList<User>();
    userRepository.findAll().forEach(users::add);
    return users;

    // Using queryDSL

    // QUser user = QUser.user;
    // JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    // return queryFactory
    // .selectFrom(user)
    // .fetch();
  }

  public User findById(Long userId) {
    // Optional<User> optionalUser = userRepository.findById(userId);
    // User user = optionalUser.get();
    // return user;
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found"));
    // return userRepository.findById(userId);
  }
}
