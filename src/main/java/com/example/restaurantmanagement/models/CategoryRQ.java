package com.example.restaurantmanagement.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;

@Data
public class CategoryRQ {
  @NotNull
  @Length(max = 100)
  private String name;
  private ArrayList<Integer> dishIds;
}
