package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TrainerProfile;
import com.example.demo.repository.TrainerProfileRepository;
import com.example.demo.service.iservice.ITrainerProfileService;

@Service
public class TrainerProfileService implements ITrainerProfileService {
	
	@Autowired
	TrainerProfileRepository trainerProfileRepository;

	@Override
	public TrainerProfile findByFullNameIs(String fullName) {
		return trainerProfileRepository.findByFullNameIs(fullName);
	}

	@Override
	public TrainerProfile findByTrainer_IdIs(Integer trainerId) {
		return trainerProfileRepository.findByTrainer_IdIs(trainerId);
	}

	@Override
	public TrainerProfile save(TrainerProfile trainerProfile) {
		return trainerProfileRepository.save(trainerProfile);
	}

}
