package com.example.restaurantmanagement.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DishRQ {
  @NotNull
  @Length(max = 100)
  private String name;
  private String image;
  private String description;
  private Float price;
}
  