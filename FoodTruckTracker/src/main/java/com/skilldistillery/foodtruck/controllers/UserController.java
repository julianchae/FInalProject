package com.skilldistillery.foodtruck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.services.UserService;

@RestController
@CrossOrigin({"*", "http://localhost"})
public class UserController {
	
	@Autowired
	private UserService userServe;

}
