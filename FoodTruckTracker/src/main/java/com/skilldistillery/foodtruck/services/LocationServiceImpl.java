package com.skilldistillery.foodtruck.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.Location;
import com.skilldistillery.foodtruck.entities.User;
import com.skilldistillery.foodtruck.repositories.LocationRepository;
import com.skilldistillery.foodtruck.repositories.UserRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Location getLocationByStreet(String street) {
		Location l = locationRepo.findByStreet(street);
		return l;
	}

	@Override
	public Location create(String username, Location location) {
		 Location location1 = locationRepo.findByStreet(location.getStreet());
		User user = userRepo.findByUsername(username);
		  if (user != null) {
			  if (location1 != null) {
				  
				  user.setLocation(location1);
				  return location1;
			  }else {
				  
				  user.setLocation(location);
				  return locationRepo.saveAndFlush(location);
			  }
		  }
		  return null;
		}

	@Override
	public Location update(String username, int lid, Location location) {
		Optional<Location> op = locationRepo.findById(lid);
		Location location1 = null;
		User user = userRepo.findByUsername(username);
		if (op.isPresent()) {
			location1 = op.get();
			
			if (user.getLocation().getId() == lid) {
				location1.setStreet(location.getStreet());
				location1.setCity(location.getCity());
				location1.setState(location.getState());
				
				return locationRepo.saveAndFlush(location1);
			}
		}
		return null;
	}


//	@Override
//	public boolean delete(String username, int lid) {
//		Optional<Location> op = locationRepo.findById(lid);
//		Location location1 = null;
//		User user = userRepo.findByUsername(username);
//		if (op.isPresent()) {
//			location1 = op.get();
//			if (user.getLocation().getId() == lid) {
//				locationRepo.deleteById(lid);
//				op = locationRepo.findById(lid);
//				return !locationRepo.existsById(lid);
//			}
//		}
//		return false;
//	}


}
