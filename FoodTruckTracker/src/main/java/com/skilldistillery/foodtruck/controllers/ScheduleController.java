package com.skilldistillery.foodtruck.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.Schedule;
import com.skilldistillery.foodtruck.services.ScheduleService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleServe;
	
	
	@GetMapping("schedule/index")
	public List<Schedule> indexSchedule(){
		
   return scheduleServe.index();
	}
	
	@GetMapping("schedule/truck/{tid}")
	public List<Schedule> specificSchedule(
			@PathVariable int tid, HttpServletResponse resp){
		List <Schedule> schedules = null;
		
		schedules = scheduleServe.truckSchedule(tid);
		
		if(schedules == null) {
			resp.setStatus(404);
		}
		return schedules;
	}
	@GetMapping("schedule/user/truck")
	public List<Schedule> userSpecificTruckSchedule(

			Principal principal,
			HttpServletResponse resp){
		List <Schedule> schedules = null;
		
		schedules = scheduleServe.specificSchedule(principal.getName());
		
		if(schedules == null) {
			resp.setStatus(404);
		}
		return schedules;
	}
	@PostMapping("schedule/truck/{tid}/location/{lid}")
	public Schedule addSchedule(
			@RequestBody Schedule schedule,
			@PathVariable int tid,
			@PathVariable int lid,
			Principal principal,
			HttpServletResponse resp) {
		
		schedule = scheduleServe.addSchedule(tid, lid, principal.getName(), schedule);
		if(schedule == null) {
			resp.setStatus(404);
		}
		return schedule;
	}
	
	
	
	
}
