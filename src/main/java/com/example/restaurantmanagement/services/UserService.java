package com.example.restaurantmanagement.services;

import java.util.Optional;
import com.example.restaurantmanagement.entities.*;
import com.example.restaurantmanagement.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.restaurantmanagement.repositories.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import com.querydsl.core.types.Predicate;
// import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserService {

  private QUser qUser = QUser.user;

  @Autowired
  private UserRepository userRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public List<User> findAll() {
    // Using pure JPA
    // List<User> users = new ArrayList<User>();
    // userRepository.findAll().forEach(users::add);
    // return users;

    // Using queryDSL

    QUser user = QUser.user;
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    return queryFactory
        .selectFrom(user)
        .fetch();

  }

  public User findById(Integer userId) {
    Predicate predicate = qUser.id.eq(userId);
    User user = userRepository.findOne(predicate).orElse(null);
    return user;
    // return userRepository
    // .findById(userId)
    // .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User
    // not found"));
  }

  public User create(UserRQ requestData) {
    User creationUserData = User.builder()
        .first_name(requestData.getFirst_name())
        .last_name(requestData.getLast_name())
        .password(requestData.getPassword())
        .email(requestData.getEmail())
        .build();
    User createdUser = userRepository.save(creationUserData);
    return createdUser;
  }

  public User updateById(Integer userId, UpdateUserRQ requestData) {
    User user = this.findById(userId);

    String newFirstName = requestData.getFirst_name() != null ? requestData.getFirst_name() : user.getFirst_name();
    String newLastName = requestData.getLast_name() != null ? requestData.getLast_name() : user.getLast_name();
    String newEmail = requestData.getEmail() != null ? requestData.getEmail() : user.getEmail();

    user.setFirst_name(newFirstName);
    user.setLast_name(newLastName);
    user.setEmail(newEmail);

    User updatedUser = userRepository.save(user);
    return updatedUser;
  }

  public void deleteById(Integer userId) {
    userRepository.deleteById(userId);
  }
}
