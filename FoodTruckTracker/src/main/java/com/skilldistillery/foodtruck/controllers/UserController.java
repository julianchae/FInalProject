package com.skilldistillery.foodtruck.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.User;
import com.skilldistillery.foodtruck.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost" })
public class UserController {

	@Autowired
	private UserService userServe;

// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
//	@GetMapping("test/users/{userId}")
//	public User getUserForTest(@PathVariable Integer userId, HttpServletResponse res) {
//		User user = userServe.getUserById(userId);
//		if (user == null) {
//			res.setStatus(404);
//		}
//		return user;
//	}
	
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
	
	@GetMapping("user/{username}")
	public User getUserByUsername(@PathVariable String username, HttpServletResponse resp) {
		User user = userServe.getUserByUsername(username);

		if (user == null) {
			resp.setStatus(404);
		}
		return user;
	}
	@PostMapping("user/{user}")
	public User createUser(@RequestBody User user, HttpServletResponse resp) {

		return user;
	}
	
	
	@PutMapping("users/{id}")
	public User updateUser(Principal principal, @RequestBody User user, @PathVariable int id) {
		return userServe.update(principal.getName(), id, user);
	}
	
	@DeleteMapping("users/{id}")
	public void delete(Principal principal, HttpServletResponse resp, @PathVariable int id) {
		if(userServe.delete(principal.getName(), id)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}
	
	
	
}
