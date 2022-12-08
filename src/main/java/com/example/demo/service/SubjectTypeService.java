package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SubjectType;
import com.example.demo.repository.SubjectTypeRepository;
import com.example.demo.service.iservice.ISubjectTypeService;

@Service
public class SubjectTypeService implements ISubjectTypeService {

	@Autowired
	private SubjectTypeRepository subjectTypeRepository;

	@Override
	public List<SubjectType> findAll() {
		return subjectTypeRepository.findAll();
	}

	@Override
	public SubjectType findBySubjectTypeName(String subjectTypeName) {
		return subjectTypeRepository.findBySubjectTypeName(subjectTypeName);
	}

	@Override
	public SubjectType save(SubjectType subjectType) {
		return subjectTypeRepository.save(subjectType);
	}

}
