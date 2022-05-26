package com.skilldistillery.foodtruck.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.repositories.TruckRepository;
@Service
public class TruckServiceImpl implements TruckService {

	
	@Autowired
	private TruckRepository truckRepo;
	
	@Override
	public List<FoodTruck> getAllTrucks() {
		
	return truckRepo.findAll();
		
		
	}

	@Override
	public List<FoodTruck> getUsersTrucks(String username) {
		
		
		return truckRepo.findByUser_Username(username);
		
		
	}

}
