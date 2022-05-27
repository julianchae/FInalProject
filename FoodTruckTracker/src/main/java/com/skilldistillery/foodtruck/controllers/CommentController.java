package com.skilldistillery.foodtruck.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.Comment;
import com.skilldistillery.foodtruck.services.CommentService;


@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class CommentController {

	@Autowired
	private CommentService commentServe;
	
	
	@GetMapping("comments/index")
	private List<Comment> index(){
		
		return commentServe.findAllComments();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
