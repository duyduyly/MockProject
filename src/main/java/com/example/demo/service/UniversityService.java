package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.iservice.IUniversityService;

@Service
public class UniversityService implements IUniversityService{
	
	@Autowired
	UniversityRepository universityRepository;

	@Override
	public University findByUniversityName(String universityName) {
		return universityRepository.findByUniversityName(universityName);
	}

	@Override
	public University save(University university) {
		return universityRepository.save(university);
	}
	
	@Override
	public List<University> findAll() {
		return universityRepository.findAll();
	}

}
