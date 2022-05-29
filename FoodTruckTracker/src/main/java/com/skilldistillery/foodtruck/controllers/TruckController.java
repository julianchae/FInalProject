package com.skilldistillery.foodtruck.controllers;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.entities.MenuItem;
import com.skilldistillery.foodtruck.services.TruckService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
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
	
	//users can create a food truck
	
	@PostMapping("trucks")
	public FoodTruck createNewFoodTruck(
			Principal principal,
			@RequestBody FoodTruck foodtruck,
			HttpServletResponse resp,
			HttpServletRequest req) {
		
		
		foodtruck= truckServe.addFoodTruck(principal.getName(), foodtruck);
		
		if (foodtruck == null) {
			resp.setStatus(404);
		}else {
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(foodtruck.getId());
			resp.setHeader("Location", url.toString());
			
		}return foodtruck;
		
	}
	
	@GetMapping("trucks/single/{tid}")
	public FoodTruck getSingleFoodtruck
		(@PathVariable int tid,
			HttpServletResponse resp) {
		FoodTruck truck = null;
		truck = truckServe.getSingleTruck(tid);

		if (truck == null) {
			resp.setStatus(404);
		}
		return truck;
		
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
	@PutMapping("trucks/menuItem/{tid}/{mid}")
	public MenuItem updateMenuItem(
			Principal principal,
			@RequestBody MenuItem menuItem, 
			@PathVariable int mid,
			@PathVariable int tid,
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
	@PostMapping("trucks/menuItem/{tid}")
	public MenuItem addMenuItem(
			Principal principal,
			@RequestBody MenuItem item,
			@PathVariable int tid,
			HttpServletRequest req, 
			HttpServletResponse resp
			) {
		
		 item = truckServe.addMenuItem(item, principal.getName(), tid);
		
		if(item== null) {
			resp.setStatus(404);
		}
		return item;
	}
	
	@GetMapping("trucks/menuItems/{tid}")
	public List<MenuItem> getMenuItemsForTruck(
			HttpServletRequest req, 
			HttpServletResponse resp,
			@PathVariable int tid
			) {
		List<MenuItem> menu = truckServe.getFullMenuForSpecificTruck(tid);
		if(menu== null) {
			resp.setStatus(404);
		}
		return menu;
	}

		
		
				
	}
	

