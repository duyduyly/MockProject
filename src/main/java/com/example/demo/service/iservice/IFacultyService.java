package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.Faculty;

public interface IFacultyService {

	Faculty findByFacultyName(String facultyName);

	List<Faculty> findAll();

	Faculty save(Faculty faculty);

}
