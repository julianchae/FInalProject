package com.skilldistillery.foodtruck.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.User;
import com.skilldistillery.foodtruck.services.UserService;

@RestController
@CrossOrigin({"*", "http://localhost"})
public class UserController {
	
	@Autowired
	private UserService userServe;
	
	// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
	@GetMapping("test/users/{userId}")
	public User getUserForTest(
	  @PathVariable Integer userId,
	  HttpServletResponse res
	) {
	  User user = userServe.getUserById(userId);
	  if (user == null) {
	    res.setStatus(404);
	  }
	  return user;
	}

}
