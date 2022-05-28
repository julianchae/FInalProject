package com.skilldistillery.foodtruck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodtruck.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

	List<Schedule> findByFoodTruckId(int tid);
	
	List<Schedule> findByFoodTruck_User_Username(String username);
	
	
}
