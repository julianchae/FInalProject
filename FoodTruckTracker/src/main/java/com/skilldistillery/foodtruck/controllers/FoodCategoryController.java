package com.skilldistillery.foodtruck.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.FoodCategory;
import com.skilldistillery.foodtruck.services.FoodCatService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class FoodCategoryController {
	
	@Autowired
	private FoodCatService foodCatServe;
	
	@GetMapping("foodcategory/index")
	public List<FoodCategory> getAllFoodCategories(){
		
		
	return foodCatServe.getAll();
	}
	

}
