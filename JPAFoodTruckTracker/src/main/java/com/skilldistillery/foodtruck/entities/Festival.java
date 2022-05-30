package com.skilldistillery.foodtruck.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Festival {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	@Column(name = "festival_date")
	private LocalDate date;
	@Column(name = "start_time")
	private LocalTime time;
	
	private String description;
	
	@Column(name = "img_url")
	private String imgUrl;
	
	@Column(name = "created_date")
	private LocalDate createdDate;
	
	
	
	@ManyToMany
	@JoinTable(name="festival_has_food_truck",
	joinColumns = @JoinColumn(name="festival_id"),
	inverseJoinColumns = @JoinColumn(name="food_truck_id"))
	private List<FoodTruck> foodTrucks;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	
	public Festival() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public List<FoodTruck> getFoodTrucks() {
		return foodTrucks;
	}

	public void setFoodTrucks(List<FoodTruck> foodTrucks) {
		this.foodTrucks = foodTrucks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		Festival other = (Festival) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Festival [id=" + id + ", name=" + name + ", date=" + date + ", time=" + time + ", description="
				+ description + ", imgUrl=" + imgUrl + ", createdDate=" + createdDate + "]";
	}
	
	
}
