package com.skilldistillery.foodtruck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.Location;
import com.skilldistillery.foodtruck.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository repo;

	@Override
	public Location getLocationByStreet(String street) {
		Location l = repo.findByStreet(street);
		return l;
	}
	
	
//	@Override
//	public Location getLocationByStreet(String street) {
//		Optional<Location> op = repo.findByStreet(street);
//		Location l = null;
//		if (op.isPresent()) {
//			l = op.get();
//		}
//		return l;
//	}

}
