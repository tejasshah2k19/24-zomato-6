package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer restaurantId;
	String title;
	String category;
	String description;
	String timings;
	String address;
	String contactNum;
	Float latitude;
	Float longitude;
	Integer pincode;
	Integer online;
	String email;
	String password;
	Integer active;
	String restaurantImagePath;
}
