package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClassStatus;
import com.example.demo.repository.ClassStatusRepository;
import com.example.demo.service.iservice.IClassStatusService;

@Service
public class ClassStatusService implements IClassStatusService {

	@Autowired
	private ClassStatusRepository classStatusRepository;

	@Override
	public List<ClassStatus> findAll() {
		return classStatusRepository.findAll();
	}

	@Override
	public ClassStatus findByClassStatusName(String name) {
		return classStatusRepository.findByClassStatusName(name);
	}

	@Override
	public ClassStatus save(ClassStatus classStatus) {
		return classStatusRepository.save(classStatus);
	}

}
