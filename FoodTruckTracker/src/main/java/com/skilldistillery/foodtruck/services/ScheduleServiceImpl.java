package com.skilldistillery.foodtruck.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodtruck.entities.FoodTruck;
import com.skilldistillery.foodtruck.entities.Schedule;
import com.skilldistillery.foodtruck.repositories.LocationRepository;
import com.skilldistillery.foodtruck.repositories.ScheduleRepository;
import com.skilldistillery.foodtruck.repositories.TruckRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	
	@Autowired
	private ScheduleRepository sRepo;
	@Autowired
	private TruckRepository tRepo;
	@Autowired
	private LocationRepository lRepo;
	
	@Override
	public List<Schedule> index() {
		
	return	sRepo.findAll();
		
		
	}
	@Override
	public List<Schedule> truckSchedule(int tid) {
		
		return sRepo.findByFoodTruckId(tid);
	}


	@Override
	public List<Schedule> specificSchedule(String username) {
		
		return sRepo.findByFoodTruck_User_Username(username);
	}

	@Override
	public Schedule addSchedule(
			int tid,
			int lid,
			String username,
			Schedule schedule) {
		
		List<FoodTruck>foodtrucks=
				tRepo.findByUser_Username(username);
		for (FoodTruck foodTruck : foodtrucks) {
			if(foodTruck.getId() == tid) {
				
			List<Schedule> truckSchedule = foodTruck.getSchedules();
				schedule.setFoodTruck(foodTruck);
				schedule.setLocation(lRepo.queryById(lid));
				truckSchedule.add(schedule);
				sRepo.saveAndFlush(schedule);
				
			}
			
		}			
					return schedule;
					
				}
				
			}
		
		
		

	

	

