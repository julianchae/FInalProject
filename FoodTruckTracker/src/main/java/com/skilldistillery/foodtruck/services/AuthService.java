package com.skilldistillery.foodtruck.services;

import com.skilldistillery.foodtruck.entities.User;

public interface AuthService {
	
	public User register(User user);
	public User getUserByUsername(String username);

}
