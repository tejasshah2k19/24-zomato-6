package com.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginDto;
import com.entity.CustomerEntity;
import com.entity.RestaurantEntity;
import com.respository.CustomerRepository;
import com.respository.RestaurantRepository;
import com.service.TokenService;

@RestController
@RequestMapping("/api/public/session")
public class SessionController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	TokenService tokenService;

	@PostMapping("/customercreate")
	public ResponseEntity<CustomerEntity> customerSignup(@RequestBody CustomerEntity customerEntity) {

		// email present ?

		Optional<CustomerEntity> customerOp = customerRepository.findByEmail(customerEntity.getEmail());
		if (customerOp.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(customerEntity);
		} else {
			customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
			customerRepository.save(customerEntity);
			return ResponseEntity.ok(customerEntity);
		}
	}

	@PostMapping("/restaurantcreate")
	public RestaurantEntity addNewRestaurant(@RequestBody RestaurantEntity restaurantEntity) {
		restaurantEntity.setActive(1);// active : true
		restaurantEntity.setOnline(0);// online : false
		restaurantRepository.save(restaurantEntity);
		return restaurantEntity;
	}

	@PostMapping("/customerlogin")
	public ResponseEntity<?> customerLogin(@RequestBody LoginDto loginDto) {
		Optional<CustomerEntity> op = customerRepository.findByEmail(loginDto.getEmail());
		if (op.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDto);
		} else {
			CustomerEntity customerEntity = op.get();
			if (passwordEncoder.matches(loginDto.getPassword(), customerEntity.getPassword())) {
				HashMap<String, Object> map = new HashMap<>();

				String authToken = tokenService.generateToken();
				customerEntity.setToken(authToken);
				customerRepository.save(customerEntity);

				map.put("restaurants", restaurantRepository.findAll());
				map.put("customer", customerEntity);
				
				return ResponseEntity.ok(map);

			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDto);
			}
		}
	 
	}

}
