package com.skilldistillery.foodtruck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer>{

}
