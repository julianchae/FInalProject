package com.skilldistillery.foodtruck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.FoodTruck;

public interface TruckRepository extends JpaRepository<FoodTruck, Integer> {
	
	List<FoodTruck> findByUser_Username(String username);

}
