package com.example.restaurantmanagement.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRQ {
  private String email;
  private String first_name;
  private String last_name;
}
