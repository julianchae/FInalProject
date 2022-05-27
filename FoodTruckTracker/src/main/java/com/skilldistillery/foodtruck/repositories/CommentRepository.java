package com.skilldistillery.foodtruck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
