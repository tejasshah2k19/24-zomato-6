package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RestaurantEntity;
import com.respository.RestaurantRepository;

@RestController
@RequestMapping("/api/restaurant/") 
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@PutMapping("updaterestaurant")
	public RestaurantEntity updateRestaurant(@RequestBody RestaurantEntity restaurantEntity) {
		//
		restaurantRepository.save(restaurantEntity);
		return restaurantEntity;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllRestaurant(){
		//
		List<RestaurantEntity> allRestaurants= restaurantRepository.findAll();
		return ResponseEntity.ok(allRestaurants);
	}
	
	
}
