package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Faculty;
import com.example.demo.repository.FacultyRepository;
import com.example.demo.service.iservice.IFacultyService;

@Service
public class FacultyService implements IFacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	public Faculty findByFacultyName(String facultyName) {
		return facultyRepository.findByFacultyName(facultyName);
	}

	@Override
	public Faculty save(Faculty faculty) {
		return facultyRepository.save(faculty);
	}
	
	@Override
	public List<Faculty> findAll() {
		return facultyRepository.findAll();
	}
}
