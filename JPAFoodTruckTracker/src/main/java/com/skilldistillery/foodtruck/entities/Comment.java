package com.skilldistillery.foodtruck.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//mysql> desc comment;
//+---------------+--------------+------+-----+---------+----------------+
//| Field         | Type         | Null | Key | Default | Extra          |
//+---------------+--------------+------+-----+---------+----------------+
//| id            | int(11)      | NO   | PRI | NULL    | auto_increment |
//| comment       | varchar(300) | NO   |     | NULL    |                |
//| rating        | int(11)      | YES  |     | NULL    |                |
//| user_id       | int(11)      | NO   | MUL | NULL    |                |
//| food_truck_id | int(11)      | NO   | MUL | NULL    |                |
//| comment_date  | datetime     | YES  |     | NULL    |                |
//+---------------+--------------+------+-----+---------+----------------+

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	private Integer rating;
	
	@Column(name = "comment_date")
	private LocalDateTime commentDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="food_truck_id")
	private FoodTruck foodTruck;
	
	
	public Comment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FoodTruck getFoodTruck() {
		return foodTruck;
	}

	public void setFoodTruck(FoodTruck foodTruck) {
		this.foodTruck = foodTruck;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", rating=" + rating + ", commentDate=" + commentDate
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
		Comment other = (Comment) obj;
		return id == other.id;
	}
	
	
}
