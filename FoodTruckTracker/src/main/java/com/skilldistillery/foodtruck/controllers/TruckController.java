package com.skilldistillery.foodtruck.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.services.TruckService;

@RestController
@RequestMapping("api")
public class TruckController {
	
	
	@Autowired
	private TruckService truckServe;
	

	@GetMapping("trucks")
	public List<FoodTruck> getAllFoodtrucks(){
		
		return truckServe.getAllTrucks();
		
	}
	@GetMapping("users/trucks")
	public List<FoodTruck> getMyFoodtrucks(Principal pricipal){
		
		return truckServe.getUsersTrucks(pricipal.getName());
		
	}
}
