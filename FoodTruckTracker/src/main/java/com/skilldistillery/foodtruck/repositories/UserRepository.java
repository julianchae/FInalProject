package com.skilldistillery.foodtruck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
