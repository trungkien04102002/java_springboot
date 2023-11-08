package com.example.restaurantmanagement.controllers;

// import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.restaurantmanagement.services.UserService;
import com.example.restaurantmanagement.models.UpdateUserRQ;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.client.HttpClientErrorException;

import com.example.restaurantmanagement.entities.User;
import com.example.restaurantmanagement.models.UserRQ;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping(path = "api/v1/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<User> getUserById(@PathVariable Integer id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping()
  @ResponseBody
  public ResponseEntity<User> createUser(@RequestBody @Valid UserRQ requestData) {
    User newUser = userService.create(requestData);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  @PatchMapping("/{id}")
  @ResponseBody
  public ResponseEntity<User> updateUserById(@PathVariable Integer id, @RequestBody @Valid UpdateUserRQ requestData) {
    User updatedUser = userService.updateById(id, requestData);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<User> deleteUserById(@PathVariable Integer id) {
    User deletedUser = userService.findById(id);
    userService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
  }

}
