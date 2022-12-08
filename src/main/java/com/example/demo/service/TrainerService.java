package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Trainer;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.iservice.ITrainerService;

@Service
public class TrainerService implements ITrainerService {

	@Autowired
	private TrainerRepository trainerRepository;

	@Override
	public List<Trainer> findAll() {
		return trainerRepository.findAll();
	}

	@Override
	public Trainer findByUsername(String username) {
		return trainerRepository.findByUsername(username);
	}

	@Override
	public Trainer findByTrainerProfile_FullNameIs(String fullName) {
		return trainerRepository.findByTrainerProfile_FullNameIs(fullName);
	}

	@Override
	public Long countByAccountContaining(String account) {
		return trainerRepository.countByAccountContaining(account);
	}

	@Override
	public boolean checkIfTrainerExisted(Trainer trainer) {
		return (trainerRepository.existsByTrainerProfile_Email(trainer.getTrainerProfile().getEmail())
				|| trainerRepository.existsByTrainerProfile_Phone(trainer.getTrainerProfile().getPhone()));
	}
}
