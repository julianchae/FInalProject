package com.skilldistillery.foodtruck.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodtruck.entities.Request;
import com.skilldistillery.foodtruck.services.RequestService;


//TODO: Needs Testing in Postman
@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class RequestController {
	
	@Autowired
	RequestService serv;
	
	@GetMapping("requests")
	public List<Request> getUserRequests(Principal principal) {
		return serv.index(principal.getName());
	}
	
	@PostMapping("requests")
	public Request userCanAddRequest(Principal principal, 
			@RequestBody Request request ) {
	
		return serv.create(principal.getName(), request);
	}
	
	@PutMapping("requests/{rid}")
	public Request updateRequest(Principal principal, @RequestBody Request request, @PathVariable int rid) {
		return serv.update(principal.getName(), rid, request);
	}
	
	@DeleteMapping("requests/{rid}")
	public void destroy(Principal principal, HttpServletResponse resp, @PathVariable int rid) {
		if(serv.destroy(principal.getName(), rid)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}
	@GetMapping("requests/{tid}")
	public List <Request> getTruckRequests(Principal principal,
			 @PathVariable int tid
			){
		System.out.println("Insde GetTruckReq");
		return serv.findByTruckId(principal.getName(),tid);
	}
}
