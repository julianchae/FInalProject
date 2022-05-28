package com.skilldistillery.foodtruck.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.foodtruck.entities.Schedule;
import com.skilldistillery.foodtruck.repositories.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	
	@Autowired
	private ScheduleRepository sRepo;
	
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
	public Schedule addSchedule(int tid, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
