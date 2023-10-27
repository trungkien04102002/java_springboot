package com.example.restaurantmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class RestaurantManagementApplication {
	@RequestMapping("/")
	@ResponseBody
	String helloWorld() {
		return "Welcome to restaurant management!";
	}

	public static void main(String[] args) {

		SpringApplication.run(RestaurantManagementApplication.class, args);
	}

}
