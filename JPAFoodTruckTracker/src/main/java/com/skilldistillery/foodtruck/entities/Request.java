package com.skilldistillery.foodtruck.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Request {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String remarks;
	@Column(name = "request_placed")
	private LocalDate requestPlaced;
	@Column(name = "requested_date")
	private LocalDate requestedDate;

	private boolean accepted;

	public Request() {
		super();
	}
	//TODO: Mapping for user
	
	@ManyToOne
	@JoinColumn(name="food_truck_id")
	private FoodTruck foodTruck;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDate getRequestPlaced() {
		return requestPlaced;
	}

	public void setRequestPlaced(LocalDate requestPlaced) {
		this.requestPlaced = requestPlaced;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public FoodTruck getFoodTruck() {
		return foodTruck;
	}

	public void setFoodTruck(FoodTruck foodTruck) {
		this.foodTruck = foodTruck;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", remarks=" + remarks + ", requestPlaced=" + requestPlaced + ", requestedDate="
				+ requestedDate + ", accepted=" + accepted + "]";
	}
	
	
	
}
