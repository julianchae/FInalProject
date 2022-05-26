package com.skilldistillery.foodtruck.services;

import com.skilldistillery.foodtruck.entities.User;

public interface UserService {
	User getUserById(int userId);

	User getUserByUsername(String username);

	public User update(String username, int id, User user);

    public boolean delete(String username, int id);
    
}
