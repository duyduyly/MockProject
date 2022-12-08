package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DeliveryManager;

@Repository
public interface DeliveryManagerRepository extends JpaRepository<DeliveryManager, Integer> {
	
	Long countByAccountContaining(String account);

	Boolean existsByEmail(String email);

}
