package com.example.restaurantmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category", schema = "public")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @ManyToMany
  @JoinTable(name = "category_dish", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "dish_id"))
  private List<Dish> dishes;

  public void addDish(Dish dish) {
    this.dishes.add(dish);
    dish.getCategories().add(this);
  }

  public void removeDish(Integer dishId) {
    Dish dish = this.dishes.stream().filter(t -> t.getId() == dishId).findFirst().orElse(null);
    if (dish != null) {
      this.dishes.remove(dish);
      dish.getCategories().remove(this);
    }
  }
}
