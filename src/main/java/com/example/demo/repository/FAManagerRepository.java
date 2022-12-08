package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FAManager;

@Repository
public interface FAManagerRepository extends JpaRepository<FAManager, Integer> {
	
	Long countByAccountContaining(String account);
	
	Boolean existsByEmail(String email);
}
