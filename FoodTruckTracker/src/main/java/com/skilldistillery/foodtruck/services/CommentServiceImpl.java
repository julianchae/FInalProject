package com.skilldistillery.foodtruck.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.Comment;
import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.repositories.CommentRepository;
import com.skilldistillery.foodtruck.repositories.TruckRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private TruckRepository truckRepo;

	@Override
	public List<Comment> findAllComments() {

		return commentRepo.findAll();
	}

	@Override
	public List<Comment> findCommentOnTruck(int tid) {

		Optional<FoodTruck> op = truckRepo.findById(tid);

		FoodTruck truck = op.get();

		return truck.getComments();

	}

}
