package com.example.demo.service.iservice;

import com.example.demo.entity.TrainerProfile;

public interface ITrainerProfileService {

	TrainerProfile findByFullNameIs(String fullName);

	TrainerProfile findByTrainer_IdIs(Integer trainerId);

	TrainerProfile save(TrainerProfile trainerProfile);

}
