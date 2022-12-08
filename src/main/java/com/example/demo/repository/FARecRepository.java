package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FARec;

@Repository
public interface FARecRepository extends JpaRepository<FARec, Integer>{
	
	Long countByAccountContaining(String account);

	Boolean existsByEmail(String email);

}
