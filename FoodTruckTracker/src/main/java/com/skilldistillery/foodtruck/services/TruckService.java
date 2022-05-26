package com.skilldistillery.foodtruck.services;

import java.util.List;

import com.skilldistillery.foodtruck.entities.FoodTruck;

public interface TruckService {
	
	
	List<FoodTruck> getAllTrucks();
	
	List<FoodTruck> getUsersTrucks(String username);
	
	

}
