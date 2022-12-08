package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.Trainer;

public interface ITrainerService {

	List<Trainer> findAll();

	Trainer findByUsername(String username);

	Trainer findByTrainerProfile_FullNameIs(String fullName);

	Long countByAccountContaining(String account);

	boolean checkIfTrainerExisted(Trainer trainer);
}
