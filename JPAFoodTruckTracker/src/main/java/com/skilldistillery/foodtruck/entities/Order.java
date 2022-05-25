package com.skilldistillery.foodtruck.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pre_order")
public class Order {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="ordered_date")
	private LocalDate orderedDate;
	@Column(name="pickup_date")
	private LocalDate pickupDate;
	@Column(name="special_requests")
	private String specialRequests;
	private boolean completed;
	
	//TODO: Map out User, menu
	
	
	public Order() {
		super();
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
