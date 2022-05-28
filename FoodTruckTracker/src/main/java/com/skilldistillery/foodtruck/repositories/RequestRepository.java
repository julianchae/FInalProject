package com.skilldistillery.foodtruck.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{
	
	List<Request> findByUser_Username(String username);
	
	Request queryById(int rid);

}
