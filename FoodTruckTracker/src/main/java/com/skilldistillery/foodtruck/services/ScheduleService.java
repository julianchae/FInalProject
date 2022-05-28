package com.skilldistillery.foodtruck.services;

import java.util.List;

import com.skilldistillery.foodtruck.entities.Schedule;

public interface ScheduleService {
	

	List<Schedule> index();
	
	List<Schedule> truckSchedule(int tid);
	
	Schedule addSchedule(int tid, String username);

	List<Schedule> specificSchedule(String username);

}
