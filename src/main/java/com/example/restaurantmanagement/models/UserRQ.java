package com.example.restaurantmanagement.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRQ {
  @NotNull
  @Length(max = 100)
  private String email;
  @NotNull
  @Length(max = 100)
  private String password;
  @NotNull
  @Length(max = 100)
  private String first_name;
  private String last_name;
  private String gender;
}
