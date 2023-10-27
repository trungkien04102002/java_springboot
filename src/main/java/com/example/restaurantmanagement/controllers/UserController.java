package com.example.restaurantmanagement.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.restaurantmanagement.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.example.restaurantmanagement.entities.User;

import java.util.List;

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
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }
}
