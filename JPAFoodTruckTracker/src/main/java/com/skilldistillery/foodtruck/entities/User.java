package com.skilldistillery.foodtruck.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

//+-----------------+---------------+------+-----+---------+----------------+
//| Field           | Type          | Null | Key | Default | Extra          |
//+-----------------+---------------+------+-----+---------+----------------+
//| id              | int(11)       | NO   | PRI | NULL    | auto_increment |
//| username        | varchar(45)   | NO   | UNI | NULL    |                |
//| password        | varchar(200)  | NO   |     | NULL    |                |
//| enabled         | tinyint(4)    | NO   |     | 1       |                |
//| role            | varchar(45)   | YES  |     | NULL    |                |
//| location_id     | int(11)       | NO   | MUL | NULL    |                |
//| first_name      | varchar(45)   | YES  |     | NULL    |                |
//| last_name       | varchar(45)   | YES  |     | NULL    |                |
//| profile_img_url | varchar(2000) | YES  |     | NULL    |                |
//+-----------------+---------------+------+-----+---------+----------------+


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	
	private boolean enabled;
	private String role;
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "profile_img_url")
	private String imgUrl;

	//TODO: Needs mapping for Favorites and TaggedTruck
	
	
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	
	@OneToMany(mappedBy="user")
	private List<Festival> festivals;
	
	@OneToMany(mappedBy="user")
	private List<FoodTruck> foodTrucks;
	
	//=======================
	@OneToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	@OneToMany(mappedBy="user")
	private List<Request> requests;
	
	@OneToMany(mappedBy="user")
	private List<TaggedTruck> taggedTrucks;
	
	@ManyToMany(mappedBy="users")
	private List<FoodTruck> favFoodTrucks;
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public List<FoodTruck> getFoodTrucks() {
		return foodTrucks;
	}

	public void setFoodTrucks(List<FoodTruck> foodTrucks) {
		this.foodTrucks = foodTrucks;
	}





	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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


	public List<FoodTruck> getFavFoodTrucks() {
		return favFoodTrucks;
	}

	public void setFavFoodTrucks(List<FoodTruck> favFoodTrucks) {
		this.favFoodTrucks = favFoodTrucks;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", enabled=" + enabled + ", role=" + role + ", password="
				+ password + ", firstName=" + firstName + ", lastName=" + lastName + ", imgUrl=" + imgUrl + "]";
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
		User other = (User) obj;
		return id == other.id;
	}
}
