package com.skilldistillery.foodtruck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

	
	List<MenuItem> findByFoodTruckId(int tid);
	
	
	
	
}
