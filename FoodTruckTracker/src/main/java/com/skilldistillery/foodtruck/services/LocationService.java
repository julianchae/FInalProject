package com.skilldistillery.foodtruck.services;

import com.skilldistillery.foodtruck.entities.Location;

public interface LocationService {
	
	Location getLocationByStreet(String street);
	
	public Location create(String username, Location location);

    public Location update(String username, int lid, Location location);

//    public boolean delete(String username, int lid);

}
