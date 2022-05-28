package com.skilldistillery.foodtruck.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.Location;
import com.skilldistillery.foodtruck.services.LocationService;

//TODO: Needs Testing in Postman
@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class LocationController {
	
	@Autowired
	LocationService serv;

	 @GetMapping("locations/search/{street}")
	  public Location getLocationByStreet(@PathVariable String street, HttpServletResponse resp){
		  Location loc = serv.getLocationByStreet(street);
		  if (loc == null) {
			  resp.setStatus(404);
		  }
		  return loc;
	  }
}
