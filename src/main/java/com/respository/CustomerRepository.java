package com.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

	
	List<CustomerEntity> findByGender(String gender);//select * from customers where gender = :gender 

	List<CustomerEntity> findByGenderAndBornYear(String gender,Integer bornYear);

	Optional<CustomerEntity> findByEmail(String email);
	
	Optional<CustomerEntity> findByToken(String token);
	
	
}
