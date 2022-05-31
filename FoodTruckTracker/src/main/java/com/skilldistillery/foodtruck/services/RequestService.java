package com.skilldistillery.foodtruck.services;

import java.util.List;

import com.skilldistillery.foodtruck.entities.Request;

public interface RequestService {
	
	
	public List<Request> index(String username);
    
    public Request create(String username, Request request);

    public Request update(String username, int rid, Request request);

    public boolean destroy(String username, int rid);
    
    public List<Request> findByTruckId (String username, int tid);

}
