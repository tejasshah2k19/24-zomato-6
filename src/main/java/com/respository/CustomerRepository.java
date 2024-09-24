package com.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

}
