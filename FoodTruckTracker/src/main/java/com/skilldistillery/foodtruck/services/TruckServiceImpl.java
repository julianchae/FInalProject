package com.skilldistillery.foodtruck.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.entities.MenuItem;
import com.skilldistillery.foodtruck.entities.User;
import com.skilldistillery.foodtruck.repositories.MenuItemRepository;
import com.skilldistillery.foodtruck.repositories.TruckRepository;
import com.skilldistillery.foodtruck.repositories.UserRepository;
@Service
public class TruckServiceImpl implements TruckService {

	
	@Autowired
	private TruckRepository truckRepo;
	@Autowired
	private MenuItemRepository menuRepo;
	@Autowired
	private UserRepository userRepo;
	
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
			int mid,
			
			MenuItem menu,
			String username) {
		List<FoodTruck> foodtrucks = 
				truckRepo.findByUser_Username(username);
		
		for (FoodTruck foodtruck : foodtrucks) {
			
			for (MenuItem menuItem: foodtruck.getMenuItems()) {
				
				if(menuItem.getId()== mid) {
			menu.setFoodTruck(foodtruck);
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
				menu.setFoodTruck(foodTruck);
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
	
	@Override
	public FoodTruck addFoodTruck(String username, FoodTruck newFoodtruck) {
		List<FoodTruck>foodtrucks=
		truckRepo.findByUser_Username(username);
		User user = userRepo.findByUsername(username);
		if(user.getRole().equals("generalUser")) {
			user.setRole("foodTruckOwner");
		}
		
		newFoodtruck.setUser(user);
		newFoodtruck.setDateCreated(LocalDateTime.now());
		foodtrucks.add(newFoodtruck);
		truckRepo.saveAndFlush(newFoodtruck);
		userRepo.saveAndFlush(user);
		return newFoodtruck;
		
	}

	@Override
	public List<MenuItem> getFullMenuForSpecificTruck(int tid) {
		
	List<MenuItem> menu=	menuRepo.findByFoodTruckId(tid);
		return menu;
	}
	@Override
	public boolean deleteById(int menuId){
		boolean deleted = false;
		Optional<MenuItem> menuOpt = menuRepo.findById(menuId);

		if (menuOpt.isPresent()) {
			menuRepo.deleteById(menuId);
			deleted = true;
		}
		
		return deleted;
	}
	
}
