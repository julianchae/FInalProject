package com.skilldistillery.foodtruck.services;

import java.util.List;

import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.entities.MenuItem;


public interface TruckService {
	
	
	List<FoodTruck> getAllTrucks();
	
	List<FoodTruck> getUsersTrucks(String username);

	List<FoodTruck> getFoodTruckByKeyword(String keyword);

	MenuItem updateFoodtruckMenu(int id, MenuItem menu, String username);
	
	MenuItem addMenuItem(MenuItem menu, String username, int tid);
}
