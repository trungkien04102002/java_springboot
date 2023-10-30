package com.example.restaurantmanagement.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class CategoryRQ {
  @NotNull
  @Length(max = 100)
  private String name;
  private List<Integer> dishIds;
}
