package com.skilldistillery.foodtruck.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.Request;
import com.skilldistillery.foodtruck.entities.User;
import com.skilldistillery.foodtruck.repositories.RequestRepository;
import com.skilldistillery.foodtruck.repositories.UserRepository;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	RequestRepository requestRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public List<Request> index(String username) {
		return requestRepo.findByUser_Username(username);
	}

	@Override
	public Request create(String username, Request request) {
	  User user = userRepo.findByUsername(username);
	  if (user != null) {
	    request.setUser(user);
	    return requestRepo.saveAndFlush(request);
	  }
	  return null;
	}

	@Override
	public Request update(String username, int rid, Request request) {
		Optional<Request> op = requestRepo.findById(rid);
		Request request1 = null;
		if (op.isPresent()) {
			request1 = op.get();
			if(request1.getUser().getUsername().equals(username)) {

				request1.setAccepted(request.isAccepted());
				request1.setRemarks(request.getRemarks());
				request1.setRequestedDate(request.getRequestedDate());
				if (request.getFoodTruck() != null) {
					request1.setFoodTruck(request.getFoodTruck());
				}
				if (request.getLocation() != null) {
					request1.setLocation(request.getLocation());
				}
				return requestRepo.saveAndFlush(request1);
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int rid) {
		Optional<Request> op = requestRepo.findById(rid);
		Request request1 = null;
		if (op.isPresent()) {
			request1 = op.get();
			if(request1.getUser().getUsername().equals(username)) {
				requestRepo.deleteById(rid);
				op = requestRepo.findById(rid);
				return !op.isPresent();
			}
		}
		return false;
	}

}
