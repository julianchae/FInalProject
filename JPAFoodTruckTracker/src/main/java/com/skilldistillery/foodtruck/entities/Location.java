package com.skilldistillery.foodtruck.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//mysql> desc location;
//+--------+-------------+------+-----+---------+----------------+
//| Field  | Type        | Null | Key | Default | Extra          |
//+--------+-------------+------+-----+---------+----------------+
//| id     | int(11)     | NO   | PRI | NULL    | auto_increment |
//| street | varchar(55) | YES  |     | NULL    |                |
//| city   | varchar(65) | YES  |     | NULL    |                |
//| state  | varchar(2)  | YES  |     | NULL    |                |
//| zip    | varchar(10) | YES  |     | NULL    |                |
//+--------+-------------+------+-----+---------+----------------+

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String street;
	private String city;
	private String state;
	private String zip;
	
	@OneToMany(mappedBy="location")
	private List<Festival> festival;
	
	public Location() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public List<Festival> getFestival() {
		return festival;
	}
	public void setFestival(List<Festival> festival) {
		this.festival = festival;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip
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
		Location other = (Location) obj;
		return id == other.id;
	}
	
	

}
