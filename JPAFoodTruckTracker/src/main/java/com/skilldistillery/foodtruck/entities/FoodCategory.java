package com.skilldistillery.foodtruck.entities;

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
import javax.persistence.Table;

//mysql> desc food_category;
//+-------------+-------------+------+-----+---------+----------------+
//| Field       | Type        | Null | Key | Default | Extra          |
//+-------------+-------------+------+-----+---------+----------------+
//| id          | int(11)     | NO   | PRI | NULL    | auto_increment |
//| name        | varchar(55) | YES  |     | NULL    |                |
//| description | text        | YES  |     | NULL    |                |
//| img_url     | varchar(45) | YES  |     | NULL    |                |
//+-------------+-------------+------+-----+---------+----------------+

@Entity
@Table(name = "food_category")
public class FoodCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@Column(name ="img_url")
	private String imgUrl;

	
	@ManyToMany
	@JoinTable(name="food_truck_has_food_category",
	joinColumns = @JoinColumn(name="food_category_id"),
	inverseJoinColumns = @JoinColumn(name="food_truck_id"))
	private List<FoodTruck> foodTrucks;
	
	//TODO: Maybe map to user
	
	public FoodCategory() {
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

	public List<FoodTruck> getFoodTrucks() {
		return foodTrucks;
	}

	public void setFoodTrucks(List<FoodTruck> foodTrucks) {
		this.foodTrucks = foodTrucks;
	}

	@Override
	public String toString() {
		return "FoodCategory [id=" + id + ", name=" + name + ", description=" + description + ", imgUrl=" + imgUrl
				+ "]";
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
		FoodCategory other = (FoodCategory) obj;
		return id == other.id;
	}
	
	

}
