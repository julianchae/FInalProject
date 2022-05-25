package com.skilldistillery.foodtruck.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//mysql> desc schedule;
//+---------------+----------+------+-----+---------+----------------+
//| Field         | Type     | Null | Key | Default | Extra          |
//+---------------+----------+------+-----+---------+----------------+
//| id            | int(11)  | NO   | PRI | NULL    | auto_increment |
//| arrival       | datetime | YES  |     | NULL    |                |
//| departure     | datetime | YES  |     | NULL    |                |
//| description   | text     | YES  |     | NULL    |                |
//| food_truck_id | int(11)  | NO   | MUL | NULL    |                |
//| location_id   | int(11)  | NO   | MUL | NULL    |                |
//+---------------+----------+------+-----+---------+----------------+

@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime arrival;
	
	private LocalDateTime departure;
	
	private String description;
	
	//TODO:Needs mapping for Location 
	
	@ManyToOne
	@JoinColumn(name="food_truck_id")
	private FoodTruck foodTruck;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

	public Schedule() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	public String toString() {
		return "Schedule [id=" + id + ", arrival=" + arrival + ", departure=" + departure + ", description="
				+ description + "]";
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
		Schedule other = (Schedule) obj;
		return id == other.id;
	}
	
}
