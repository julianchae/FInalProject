package com.skilldistillery.foodtruck.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.entities.MenuItem;
import com.skilldistillery.foodtruck.repositories.MenuItemRepository;
import com.skilldistillery.foodtruck.services.TruckService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost" })
public class TruckController {
	
	
	@Autowired
	private TruckService truckServe;
	@Autowired
	private MenuItemRepository menuRepo;
	
	@GetMapping("trucks")
	public List<FoodTruck> getAllFoodtrucks(){
		
		return truckServe.getAllTrucks();
	}
	@GetMapping("users/trucks")
	public List<FoodTruck> getMyFoodtrucks(Principal pricipal){
		return truckServe.getUsersTrucks(pricipal.getName());
	}
	@GetMapping("trucks/{keyword}")
	public List<FoodTruck> getFoodtrucksByKeyword(
			@PathVariable String keyword,
			HttpServletResponse resp){
		List<FoodTruck> foodtrucks = truckServe.getFoodTruckByKeyword(keyword);
		 if (foodtrucks == null) {
				resp.setStatus(404);
			}
			return foodtrucks;
	}
	
	
	
	
	@PutMapping("trucks/menuItem/{mid}")
	public MenuItem addMenuItem(
			Principal principal,
			@RequestBody MenuItem menuItem, @PathVariable
			int mid,
			HttpServletRequest req, 
			HttpServletResponse resp) {
		
				menuItem =
				truckServe.updateFoodtruckMenu(mid,
				menuItem, 
				principal.getName());
		
	if(menuItem == null) {
		 resp.setStatus(404);
	}
	return menuItem;

		
		
		
	}
}
