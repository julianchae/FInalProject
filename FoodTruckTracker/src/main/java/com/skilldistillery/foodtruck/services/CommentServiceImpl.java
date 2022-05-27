package com.skilldistillery.foodtruck.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.Comment;
import com.skilldistillery.foodtruck.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private CommentRepository commentRepo;
	
	@Override
	public List<Comment> findAllComments(){
		
		
		return commentRepo.findAll();
	}
	
	
	
}
