package com.skilldistillery.foodtruck.services;

import java.util.List;

import com.skilldistillery.foodtruck.entities.Comment;

public interface CommentService {

	List<Comment> findAllComments();

	List<Comment> findCommentOnTruck(int tid);
	
	

}
