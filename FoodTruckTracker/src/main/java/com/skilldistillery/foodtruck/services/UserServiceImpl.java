package com.skilldistillery.foodtruck.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.User;
import com.skilldistillery.foodtruck.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User getUserById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
		return user;}
		
		return null;
	}

}
