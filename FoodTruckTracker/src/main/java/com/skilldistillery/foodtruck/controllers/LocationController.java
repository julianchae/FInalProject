package com.skilldistillery.foodtruck.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.Location;
import com.skilldistillery.foodtruck.entities.Request;
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
	 
		@PostMapping("locations")
		public Location addLocation(Principal principal,
				@RequestBody Location location) {
			System.out.println(location.getCity());
			System.out.println(location.getStreet());
			return serv.create(principal.getName(), location);
		}
		
		@PutMapping("locations/{lid}")
		public Location updateLocation(Principal principal, @RequestBody Location location, @PathVariable int lid) {
			return serv.update(principal.getName(), lid, location);
		}
//		
//		@DeleteMapping("locations/{lid}")
//		public void delete(Principal principal, HttpServletResponse resp, @PathVariable int lid) {
//			if(serv.delete(principal.getName(), lid)) {
//				resp.setStatus(204);
//			} else {
//				resp.setStatus(404);
//			}
//		}
}
