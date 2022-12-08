package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TrainerProfile;

@Repository
public interface TrainerProfileRepository extends JpaRepository<TrainerProfile, Integer> {
	
	TrainerProfile findByFullNameIs (String fullName);
	
	TrainerProfile findByTrainer_IdIs (Integer trainerId);

}
