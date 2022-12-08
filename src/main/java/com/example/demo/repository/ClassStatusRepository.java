package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClassStatus;

@Repository
public interface ClassStatusRepository extends JpaRepository<ClassStatus, Integer> {
	
	ClassStatus findByClassStatusName(String classStatusName);
}
