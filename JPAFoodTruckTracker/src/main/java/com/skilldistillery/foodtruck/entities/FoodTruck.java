package com.skilldistillery.foodtruck.entities;

import java.util.List;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="food_truck")
public class FoodTruck {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name = "img_url")
	private String imgUrl;
	
	private boolean active;
	@Column(name = "date_created")
	private String dateCreated;
	@Column(name = "website_url")
	private String websiteUrl;
		
	@OneToMany(mappedBy="foodTruck")
	private List<Comment> comments; 
	
	@ManyToMany(mappedBy="foodTrucks")
	private List<Festival> festivals;
	
	@ManyToMany(mappedBy="foodTrucks")
	private List<FoodCategory> foodCategories;
	
	@OneToMany(mappedBy="foodTruck")
	private List<Schedule> schedules;
	
	@OneToMany(mappedBy="foodTruck")
	private List<MenuItem> menuItems;
	
	@OneToMany(mappedBy="foodTruck")
	private List<Request> requests;
	
	@OneToMany(mappedBy="foodTruck")
	private List<TaggedTruck> taggedTrucks;
	
	@ManyToMany(mappedBy="favFoodTrucks")
	private List<User> users;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	

	public FoodTruck() {
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


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getWebsiteUrl() {
		return websiteUrl;
	}


	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<Festival> getFestivals() {
		return festivals;
	}


	public void setFestivals(List<Festival> festivals) {
		this.festivals = festivals;
	}


	public List<FoodCategory> getFoodCategories() {
		return foodCategories;
	}


	public void setFoodCategories(List<FoodCategory> foodCategories) {
		this.foodCategories = foodCategories;
	}


	public List<Schedule> getSchedules() {
		return schedules;
	}


	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}


	public List<MenuItem> getMenuItems() {
		return menuItems;
	}


	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}


	public List<Request> getRequests() {
		return requests;
	}


	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}


	public List<TaggedTruck> getTaggedTrucks() {
		return taggedTrucks;
	}


	public void setTaggedTrucks(List<TaggedTruck> taggedTrucks) {
		this.taggedTrucks = taggedTrucks;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<User> getUsersWhoFavorited() {
		return users;
	}


	public void setUsersWhoFavorited(List<User> users) {
		this.users = users;
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
		FoodTruck other = (FoodTruck) obj;
		return id == other.id;
	}
	
	
	
}
