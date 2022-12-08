package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClassAdminProfile;
import com.example.demo.repository.ClassAdminProfileRepository;
import com.example.demo.service.iservice.IClassAdminProfileService;

@Service
public class ClassAdminProfileService implements IClassAdminProfileService{
	
	@Autowired
	private ClassAdminProfileRepository classAdminProfileRepository;

	@Override
	public ClassAdminProfile findByFullName(String fullName) {
		return classAdminProfileRepository.findByFullName(fullName);
	}

	@Override
	public ClassAdminProfile findByClassAdmin_IdIs(Integer classAdminId) {
		return classAdminProfileRepository.findByClassAdmin_IdIs(classAdminId);
	}

	@Override
	public ClassAdminProfile save(ClassAdminProfile classAdminProfile) {
		return classAdminProfileRepository.save(classAdminProfile);
	}

}
