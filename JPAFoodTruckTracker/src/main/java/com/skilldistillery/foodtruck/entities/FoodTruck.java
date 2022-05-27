package com.skilldistillery.foodtruck.entities;

import java.time.LocalDateTime;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="food_truck")
public class FoodTruck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name = "img_url")
	private String imgUrl;
	
	private boolean active;
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	@Column(name = "website_url")
	private String websiteUrl;
	@JsonIgnore
	@OneToMany(mappedBy="foodTruck")
	private List<Comment> comments; 
	@JsonIgnore
	@ManyToMany(mappedBy="foodTrucks")
	private List<Festival> festivals;
	@JsonIgnore
	@ManyToMany(mappedBy="foodTrucks")
	private List<FoodCategory> foodCategories;
	@JsonIgnore
	@OneToMany(mappedBy="foodTruck")
	private List<Schedule> schedules;
	@JsonIgnore
	@OneToMany(mappedBy="foodTruck")
	private List<MenuItem> menuItems;
	@JsonIgnore
	@OneToMany(mappedBy="foodTruck")
	private List<Request> requests;
	@JsonIgnore
	@OneToMany(mappedBy="foodTruck")
	private List<TaggedTruck> taggedTrucks;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	@ManyToMany
	@JoinTable(
			name="favorites",
			joinColumns = @JoinColumn(name="food_truck_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> users;
	
	

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


	public LocalDateTime getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(LocalDateTime dateCreated) {
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


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
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
