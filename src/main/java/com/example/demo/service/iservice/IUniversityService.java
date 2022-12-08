package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.University;

public interface IUniversityService {

	University findByUniversityName(String universityName);
	
	List<University> findAll();

	University save(University university);

}
