package com.skilldistillery.foodtruck.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.Comment;
import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.entities.User;
import com.skilldistillery.foodtruck.repositories.CommentRepository;
import com.skilldistillery.foodtruck.repositories.TruckRepository;
import com.skilldistillery.foodtruck.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TruckRepository truckRepo;
	@Autowired
	private CommentRepository commentRepo;

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

	@Override
	public User update(String username, int id, User user) {
		Optional<User> op = userRepo.findById(id);
		User user1 = null;
		if (op.isPresent()) {
			user1 = op.get();
			if(user1.getUsername().equals(username)) {
				user1.setFirstName(user.getFirstName());
				user1.setLastName(user.getLastName());
				user1.setImgUrl(user.getImgUrl());
				return userRepo.saveAndFlush(user1);
			}
		}
		return null;
	}

	@Override
	public boolean delete(String username, int id) {
		Optional<User> op = userRepo.findById(id);
		User user1 = null;
		if (op.isPresent()) {
			user1 = op.get();
			if(user1.getUsername().equals(username)) {
				userRepo.deleteById(id);
				op = userRepo.findById(id);
				return !op.isPresent();
			}
		}
		return false;
	}

	@Override
	public Comment createCommentOnTruck(String name, 
			int tid, 
			Comment comment) {
		User user =userRepo.findByUsername(name);
		comment.setUser(user);
		Optional<FoodTruck> op = truckRepo.findById(tid);
		FoodTruck truck=op.get();		
		comment.setFoodTruck(truck);
		List<Comment>userComments=	user.getComments();
		userComments.add(comment);
		return commentRepo.saveAndFlush(comment);
		
	}

}
