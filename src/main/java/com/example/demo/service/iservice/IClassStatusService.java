package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.ClassStatus;

public interface IClassStatusService {

	List<ClassStatus> findAll();
	
	ClassStatus findByClassStatusName(String name);

	ClassStatus save(ClassStatus classStatus);
}
