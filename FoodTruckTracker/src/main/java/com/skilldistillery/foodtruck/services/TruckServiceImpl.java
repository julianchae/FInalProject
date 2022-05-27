package com.skilldistillery.foodtruck.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.entities.MenuItem;
import com.skilldistillery.foodtruck.repositories.MenuItemRepository;
import com.skilldistillery.foodtruck.repositories.TruckRepository;
@Service
public class TruckServiceImpl implements TruckService {

	
	@Autowired
	private TruckRepository truckRepo;
	@Autowired
	private MenuItemRepository menuRepo;
	@Override
	public List<FoodTruck> getAllTrucks() {
		
	return truckRepo.findAll();
		
	}

	@Override
	public List<FoodTruck> getUsersTrucks(String username) {
		
		return truckRepo.findByUser_Username(username);
	
	}
	@Override
	public List<FoodTruck> getFoodTruckByKeyword(String keyword){
		
		keyword= "%" + keyword+ "%";
		
		return truckRepo.findByNameLike(keyword);
	}
	@Override
	public MenuItem updateFoodtruckMenu(
			int id,
			MenuItem menu,
			String username) {
		List<FoodTruck> foodtrucks = 
				truckRepo.findByUser_Username(username);
		
		for (FoodTruck foodtruck : foodtrucks) {
			
			for (MenuItem menuItem: foodtruck.getMenuItems()) {
				
				if(menuItem.getId()== id) {
					menu.setId(menuItem.getId());
					return menuRepo.saveAndFlush(menu);
				}
			}
		}
		return null;
	}

	@Override
	public MenuItem addMenuItem(MenuItem menu, String username, int tid) {
		
		List<FoodTruck>foodtrucks=
				truckRepo.findByUser_Username(username);
		for (FoodTruck foodTruck : foodtrucks) {
			if(foodTruck.getId()== tid) {
				List<MenuItem> menuItems = foodTruck.getMenuItems();
				menuItems.add(menu);
				return menuRepo.saveAndFlush(menu);
				}
			}
			
		return menu;
		
		
	}
	
	@Override
	public FoodTruck getSingleTruck(int tid) {
		FoodTruck truck = new FoodTruck();
		Optional<FoodTruck> op = truckRepo.findById(tid);
			truck=op.get();		
		return truck;
	}

		
		
	
	
}
