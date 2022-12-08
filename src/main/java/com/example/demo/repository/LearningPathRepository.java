package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LearningPath;

@Repository
public interface LearningPathRepository extends JpaRepository<LearningPath, Integer> {
	
	@Query("SELECT l.skill FROM LearningPath l")
	Set<String> findAllSkills();

}
