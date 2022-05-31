package com.skilldistillery.foodtruck.services;

import java.util.List;

import com.skilldistillery.foodtruck.entities.Schedule;

public interface ScheduleService {
	

	List<Schedule> index();
	
	List<Schedule> truckSchedule(int tid);
	
	Schedule addSchedule(int tid, int lid, String username, Schedule schedule);

	List<Schedule> specificSchedule(String username);

	Schedule updateSchedule(int tid, int lid, String name, Schedule schedule);

}
