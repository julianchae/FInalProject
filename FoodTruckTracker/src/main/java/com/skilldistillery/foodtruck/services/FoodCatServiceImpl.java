package com.skilldistillery.foodtruck.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.FoodCategory;
import com.skilldistillery.foodtruck.repositories.FoodCategoryRepository;
@Service
public class FoodCatServiceImpl implements FoodCatService {

	@Autowired
	private FoodCategoryRepository foodCatRepo;
	
	public List<FoodCategory> getAll(){
		
	return	foodCatRepo.findAll();
		
	}
}
