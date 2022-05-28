package com.skilldistillery.foodtruck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	Location findByStreet(String street);
	
}
