package com.skilldistillery.foodtruck.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pre_order")
public class Order {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="ordered_date")
	private LocalDateTime orderedDate;
	@Column(name="pickup_date")
	private LocalDateTime pickupDate;
	@Column(name="special_requests")
	private String specialRequests;
	private boolean completed;
	
	
	@ManyToOne
	@JoinColumn(name="menu_item_id")
	private MenuItem menuItem;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Order() {
		super();
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}



	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}



	public LocalDateTime getPickupDate() {
		return pickupDate;
	}



	public void setPickupDate(LocalDateTime pickupDate) {
		this.pickupDate = pickupDate;
	}



	public String getSpecialRequests() {
		return specialRequests;
	}



	public void setSpecialRequests(String specialRequests) {
		this.specialRequests = specialRequests;
	}



	public boolean isCompleted() {
		return completed;
	}



	public void setCompleted(boolean completed) {
		this.completed = completed;
	}



	public MenuItem getMenuItem() {
		return menuItem;
	}



	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
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
		Order other = (Order) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderedDate=" + orderedDate + ", pickupDate=" + pickupDate + ", specialRequests="
				+ specialRequests + ", completed=" + completed + "]";
	}
	
	
	

	
}
