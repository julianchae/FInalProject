package com.skilldistillery.foodtruck.services;

import com.skilldistillery.foodtruck.entities.Location;

public interface LocationService {
	
	Location getLocationByStreet(String street);

}
